package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.models.Summoner
import rx.Observable

public interface MainView {
    fun summonerName(): Observable<CharSequence>
    fun selectedRegion(): Observable<Int>
    fun action(): Observable<Any>

    fun didEnableSubmit(isEnabled: Boolean)
    fun didUpdateSelectedRegion(region: String)
    fun didUpdateRegions(regions: List<String>)
    fun didUpdateSummoner(summoner: Summoner)
}
