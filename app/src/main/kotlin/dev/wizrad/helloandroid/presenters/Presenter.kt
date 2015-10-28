package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.utilities.Subscriptions

abstract class Presenter : PresenterType {

  //
  // region Properties
  //

  override val subscriptions = Subscriptions()
  override val isActive: Boolean get() = !subscriptions.isUnsubscribed

  // endregion

  //
  // region Lifecycle
  //

  override fun initialize() {

  }

  override fun becomeActive() {
    subscriptions.prepareToSubscribe()
    this.didBecomeActive()
  }

  protected open fun didBecomeActive() {

  }

  override fun resignActive() {
    subscriptions.unsubscribe()
    this.didResignActive()
  }

  protected open fun didResignActive() {

  }

  // endregion

}
