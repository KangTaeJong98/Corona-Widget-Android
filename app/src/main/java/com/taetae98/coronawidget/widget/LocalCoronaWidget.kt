package com.taetae98.coronawidget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.dto.LocalCoronaWidgetInformation
import com.taetae98.coronawidget.manager.InternetManager
import com.taetae98.coronawidget.repository.WidgetRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import javax.inject.Inject

@AndroidEntryPoint
class LocalCoronaWidget : AppWidgetProvider() {
    private val data = HashMap<String, String>()

    @Inject
    lateinit var widgetRepository: WidgetRepository

    @Inject
    lateinit var internetManager: InternetManager

    companion object {
        private const val TOTAL_CONFIRMED = "TOTAL_CONFIRMED"
        private const val CONFIRMED = "CONFIRMED"
        private const val PHASE = "PHASE"

        const val WIDGET_UPDATE = "com.taetae98.coronawidget.localcoronawidget.update"
    }

    private fun updateConfirmed(elements: Elements) {
        for (node in elements) {
            val name = node.select("span.txt_location").text()
            val totalConfirmed = node.select("strong.num_location").text()
            val confirmed = node.select("span.num_increment").text().replace("증가", "").trim()

            data["$name$TOTAL_CONFIRMED"] = totalConfirmed
            data["$name$CONFIRMED"] = confirmed
        }
    }

    private fun updatePhase(elements: Elements) {
        for (node in elements) {
            val name = node.select("span.txt_location").text()
            val phase = node.select("strong.txt_step").text()

            data["$name$PHASE"] = phase
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        when (intent.action) {
            WIDGET_UPDATE -> {
                val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, LocalCoronaWidget::class.java))
                onUpdate(context, AppWidgetManager.getInstance(context), ids)
            }
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        if (internetManager.isConnected()) {
            runBlocking(Dispatchers.IO) {
                try {
                    val document = Jsoup.connect("https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=%EC%BD%94%EB%A1%9C%EB%82%98").get()
                    val information = document.select("div.wrap_subtab:has(div.cont_info)")

                    updateConfirmed(information[0].select("ul.list_map li"))
                    updatePhase(information[1].select("ul.list_map li"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        appWidgetIds.forEach {
            appWidgetManager.updateAppWidget(it, getUpdatedView(context, it))
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        CoroutineScope(Dispatchers.IO).launch {
            widgetRepository.deleteByIdLocalCoronaWidgetInformation(appWidgetIds)
        }
    }

    private fun getUpdatedView(context: Context, id: Int): RemoteViews {
        val information = runBlocking(Dispatchers.IO) {
            widgetRepository.selectByIdLocalCoronaWidgetInformation(id) ?: LocalCoronaWidgetInformation()
        }

        val pending = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.main_navigation)
            .setDestination(R.id.confirmedFragment)
            .createPendingIntent()

        return RemoteViews(context.packageName, R.layout.widget_local_corona).apply {
            setTextColor(R.id.local, information.textColor)
            setTextColor(R.id.phase, information.textColor)
            setTextColor(R.id.total_confirmed_text_view, information.textColor)
            setTextColor(R.id.confirmed_text_view, information.textColor)
            setTextColor(R.id.total_confirmed, information.textColor)
            setTextColor(R.id.confirmed, information.textColor)

            setTextViewText(R.id.local, information.local)
            setTextViewText(R.id.phase, data["${information.local}$PHASE"]?.let { context.getString(R.string.n_phase, it) } ?: "")
            setTextViewText(R.id.total_confirmed, data["${information.local}$TOTAL_CONFIRMED"]?.let { context.getString(R.string.n_person, it) } ?: "")
            setTextViewText(R.id.confirmed, data["${information.local}$CONFIRMED"]?.let { context.getString(R.string.plus_n_person, it) } ?: "")

            setInt(R.id.layout, "setBackgroundColor", information.backgroundColor)
            setOnClickPendingIntent(R.id.layout, pending)
        }
    }
}