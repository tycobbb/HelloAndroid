package dev.wizrad.helloandroid.presenters

import rx.Observable

internal interface MainPresenterType : PresenterType {
    val regions:   Observable<List<String>>
    val canSubmit: Observable<Boolean>

    fun bindRegion(source: Observable<Int>)
    fun bindName(source: Observable<CharSequence>)
    fun bindAction(source: Observable<Any>)
}