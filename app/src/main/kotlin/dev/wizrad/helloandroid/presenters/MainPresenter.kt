package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.views.MainView
import dev.wizrad.helloandroid.services.SummonerService
import dev.wizrad.helloandroid.services.utilities.UrlComponents

import android.util.Log
import dev.wizrad.helloandroid.models.Region
import rx.Observable
import rx.subjects.BehaviorSubject
import rx.subjects.ReplaySubject

import javax.inject.Inject

internal class MainPresenter @Inject constructor(
    val view: MainView,
    val summonerService: SummonerService) : Presenter(), MainPresenterType {

    //
    // region Properties
    //

    private val _regions = Region.all()
    private val _name = BehaviorSubject.create<String>()
    private val _selectedRegion = BehaviorSubject.create<Region>()

    //
    // Input Bindings
    //

    override fun bindName(source: Observable<CharSequence>) {
        this.subscriptions.add(source
            .map { it.toString() }
            .subscribe(this._name))
    }

    override fun bindRegion(source: Observable<Int>) {
        this.subscriptions.add(source
            .map { this._regions[it] }
            .subscribe(this._selectedRegion))
    }

    override fun bindAction(source: Observable<Any>) {
        this.subscriptions.add(source
            .subscribe {
                this.fetchSummoner()
            })
    }

    //
    // External Observables
    //

    override val regions: Observable<List<String>> get() {
        return Observable.just(this._regions.map { it.code })
    }

    override val canSubmit: Observable<Boolean> get() {
        return Observable
            .combineLatest(this._selectedRegion, this._name) { region, name ->
                name.length() != 0 && region != null
            }
    }

    //
    // Services
    //

    private fun fetchSummoner() {
        this.subscriptions.add(Observable
            .combineLatest(this._selectedRegion, this._name) { region, name ->
                object { val region = region; val name = name }
            }
            .switchMap { tuple ->
                this.summonerService.fetchSummonersByName(tuple.region.code, UrlComponents(tuple.name))
            }
            .subscribe (
                { summoners -> this.view.didUpdateSummoner(summoners.values().first()) },
                { error     -> Log.e("test", "$error") }
            ))
    }
}
