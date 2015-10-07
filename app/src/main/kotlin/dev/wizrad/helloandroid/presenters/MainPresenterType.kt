package dev.wizrad.helloandroid.presenters

import rx.Observable

internal interface MainPresenterType : PresenterType {
    fun bindRegion(source: Observable<Int>)
    fun bindName(source: Observable<CharSequence>)
    fun bindAction(source: Observable<Any>)
}