package com.taetae98.coronawidget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.taetae98.coronawidget.R
import com.taetae98.coronawidget.dto.KoreaCoronaWidgetInformation
import com.taetae98.coronawidget.manager.InternetManager
import com.taetae98.coronawidget.repository.WidgetRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import javax.inject.Inject

@AndroidEntryPoint
class KoreaCoronaWidget : AppWidgetProvider() {
    private var totalConfirmed = ""
    private var confirmed = ""
    private var totalDead = ""
    private var dead = ""
    private var firstVaccine = ""
    private var secondVaccine = ""

    @Inject
    lateinit var widgetRepository: WidgetRepository

    @Inject
    lateinit var internetManager: InternetManager

    companion object {
        const val WIDGET_UPDATE = "com.taetae98.coronawidget.koreacoronawidget.update"
    }

    private fun updateConfirmed() {
        runBlocking(Dispatchers.IO) {
            try {
                val document = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%BD%94%EB%A1%9C%EB%82%9819").get()
                val information = document.select("div.main_tab_area > div.status_info > ul")
                val confirmedInformation = information.select("li.info_01")
                val deadInformation = information.select("li.info_04")

                totalConfirmed = confirmedInformation.select("p.info_num").text()
                confirmed = confirmedInformation.select("em.info_variation").text()
                totalDead = deadInformation.select("p.info_num").text()
                dead = deadInformation.select("em.info_variation").text()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateVaccine() {
        runBlocking(Dispatchers.IO) {
            try {
                val document = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EC%BD%94%EB%A1%9C%EB%82%9819%EB%B0%B1%EC%8B%A0%ED%98%84%ED%99%A9").get()
                val information = document.select("div.vaccine_status_item strong.value")

                firstVaccine = information.first()?.text() ?: ""
                secondVaccine = information.last()?.text() ?: ""
            } catch (e: Exception) {

            }
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        when (intent.action) {
            WIDGET_UPDATE -> {
                val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, KoreaCoronaWidget::class.java))
                onUpdate(context, AppWidgetManager.getInstance(context), ids)
            }
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        if (internetManager.isConnected()) {
            updateConfirmed()
            updateVaccine()

            appWidgetIds.forEach {
                appWidgetManager.updateAppWidget(it, getUpdatedView(context, it))
            }
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        CoroutineScope(Dispatchers.IO).launch {
            widgetRepository.deleteById(appWidgetIds)
        }
    }

    private fun getUpdatedView(context: Context, id: Int): RemoteViews {
        val information = runBlocking(Dispatchers.IO) {
            widgetRepository.selectByIdKoreaCoronaWidgetInformation(id) ?: KoreaCoronaWidgetInformation()
        }

        val pending = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.main_navigation)
            .setDestination(R.id.confirmedFragment)
            .createPendingIntent()

        return RemoteViews(context.packageName, R.layout.widget_korea_corona).apply {
            setTextColor(R.id.confirmed_text_view, information.textColor)
            setTextColor(R.id.total_confirmed, information.textColor)
            setTextColor(R.id.confirmed, information.textColor)
            setTextColor(R.id.dead_text_view, information.textColor)
            setTextColor(R.id.total_dead, information.textColor)
            setTextColor(R.id.dead, information.textColor)
            setTextColor(R.id.first_vaccine_text_view, information.textColor)
            setTextColor(R.id.first_vaccine, information.textColor)
            setTextColor(R.id.second_vaccine_text_view, information.textColor)
            setTextColor(R.id.second_vaccine, information.textColor)

            setTextViewText(R.id.total_confirmed, context.getString(R.string.n_person, totalConfirmed))
            setTextViewText(R.id.confirmed, context.getString(R.string.plus_n_person, confirmed))
            setTextViewText(R.id.total_dead, context.getString(R.string.n_person, totalDead))
            setTextViewText(R.id.dead, context.getString(R.string.plus_n_person, dead))
            setTextViewText(R.id.first_vaccine, "$firstVaccine%")
            setTextViewText(R.id.second_vaccine, "$secondVaccine%")

            setInt(R.id.layout, "setBackgroundColor", information.backgroundColor)
            setOnClickPendingIntent(R.id.layout, pending)
        }
    }
}