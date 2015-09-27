package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.views.MainView
import dev.wizrad.helloandroid.services.SummonerService
import dev.wizrad.helloandroid.services.utilities.UrlComponents

import android.util.Log

import javax.inject.Inject

public class MainPresenter @Inject constructor(
    val view: MainView,
    val summonerService: SummonerService) : MainPresenterType {

    override fun initialize() {
        this.summonerService
            .fetchSummonersByName("na", UrlComponents("derkis", "fartbutt"))
            .subscribe(
                { summoners -> this.view.didUpdateSummoner(summoners.values().first()) },
                { error     -> Log.e("test", "$error") }
            )
    }

}
