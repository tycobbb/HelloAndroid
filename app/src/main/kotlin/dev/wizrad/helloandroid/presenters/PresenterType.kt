package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.utilities.Subscriptions

interface PresenterType {
  val isActive: Boolean get
  val subscriptions: Subscriptions get

  fun initialize()
  fun becomeActive()
  fun resignActive()
}