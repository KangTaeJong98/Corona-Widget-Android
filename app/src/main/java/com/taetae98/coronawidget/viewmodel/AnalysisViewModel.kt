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
class AnalysisViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val document = Jsoup.connect("http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=").get()
            val table = document.select("div.caseTable")
            val chart = document.select("div.liveMoveChart.mgt16")
            val confirmed = document.select("div.c_chart.c_chart_dp")
            val sumOfConfirmed = document.select("div.c_chart.c_chart_pt")
            val release = document.select("div.c_chart.c_chart_ct")
            val data = StringBuilder()
            document.select("div.board_top.mgt16 + div.data_table").forEach {
                data.append(it.previousElementSibling(), it)
            }
            val script = document.select("script")
            withContext(Dispatchers.Main) {
                html.value = createHTML("${document.head()}$chart${sumOfConfirmed.prev()}$sumOfConfirmed${confirmed.prev()}$confirmed${release.prev()}$release${table.prev()}$table$data$script")
            }
        }
    }

    val html = stateHandle.getLiveData("HTML", "")

    private fun createHTML(html: String): String {
        return "<!DOCTYPE html><html lang=\"ko\">$html</html>".replace("/static", "http://ncov.mohw.go.kr/static")
    }
}