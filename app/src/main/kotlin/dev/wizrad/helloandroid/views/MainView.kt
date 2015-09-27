package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.models.Summoner

public interface MainView {
    fun didUpdateSummoner(summoner: Summoner)
}
