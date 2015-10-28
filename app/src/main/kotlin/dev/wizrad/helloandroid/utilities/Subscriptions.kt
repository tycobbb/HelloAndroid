package dev.wizrad.helloandroid.utilities

import rx.Subscription
import java.util.ArrayList

class Subscriptions() : Subscription {

  private var _subscriptions: MutableList<Subscription>? = null

  fun prepareToSubscribe() {
    _subscriptions = ArrayList<Subscription>()
  }

  fun add(subscription: Subscription) : Subscriptions {
    if(isUnsubscribed) {
      this.prepareToSubscribe()
    }

    _subscriptions!!.add(subscription)
    return this
  }

  //
  // Subscription
  //

  override fun isUnsubscribed(): Boolean {
    return _subscriptions == null
  }

  override fun unsubscribe() {
    if(!this.isUnsubscribed) {
      for(subscription in _subscriptions!!) {
        subscription.unsubscribe()
      }

      _subscriptions = null
    }
  }

}