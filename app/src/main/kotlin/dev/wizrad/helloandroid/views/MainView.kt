package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.models.Summoner

public interface MainView {
    fun didEnableSubmit(isEnabled: Boolean)
    fun didUpdateRegions(regions: List<String>)
    fun didUpdateSummoner(summoner: Summoner)
}
