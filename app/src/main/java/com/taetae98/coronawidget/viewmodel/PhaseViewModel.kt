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
class PhaseViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val document = Jsoup.connect("http://ncov.mohw.go.kr/regSocdisBoardView.do?brdId=6&brdGubun=68&ncvContSeq=495").get()
            val timetable = document.select("div.timetable")
            val map = document.select("div.regional_step_status")
            val script = document.body().getElementsByTag("script")

            withContext(Dispatchers.Main) {
                html.value = createHTML("${document.head()}$timetable$map$script")
            }
        }
    }

    val html = stateHandle.getLiveData("HTML", "")

    private fun createHTML(html: String): String {
        return "<!DOCTYPE html><html lang=\"ko\">$html</html>".replace("/static", "http://ncov.mohw.go.kr/static")
    }
}