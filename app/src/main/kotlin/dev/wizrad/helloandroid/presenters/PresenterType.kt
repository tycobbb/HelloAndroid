package dev.wizrad.helloandroid.presenters

internal interface PresenterType {
    val isActive: Boolean get
    val subscriptions: Subscriptions get

    fun initialize()
    fun becomeActive()
    fun resignActive()
}