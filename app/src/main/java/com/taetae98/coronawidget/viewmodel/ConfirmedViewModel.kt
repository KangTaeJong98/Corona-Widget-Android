package com.taetae98.coronawidget.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class ConfirmedViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val document = Jsoup.connect("http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=").get()
            val timetable = document.select("div.timetable")
            val map = document.select("div.regional_patient_status_A")
            val chart = document.select("div.c_chart.c_chart_rps_B")
            val script = document.body().getElementsByTag("script")

            withContext(Dispatchers.Main) {
                html.value = createHTML("${document.head()}$timetable$map$chart$script")
            }
        }
    }

    val html = stateHandle.getLiveData("HTML", "")

    private fun createHTML(html: String): String {
        return "<!DOCTYPE html><html lang=\"ko\">$html</html>".replace("/static", "http://ncov.mohw.go.kr/static")
    }
}