package dev.wizrad.helloandroid.presenters

import rx.Subscription

class Subscriptions(
    var subscriptions: MutableList<Subscription>) {

    fun add(subscription: Subscription) : Subscriptions {
        subscriptions.add(subscription)
        return this
    }
}