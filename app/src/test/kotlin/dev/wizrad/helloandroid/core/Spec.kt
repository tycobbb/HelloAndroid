package dev.wizrad.helloandroid.core

import dev.wizrad.helloandroid.dagger.components.DaggerMockRootComponent
import dev.wizrad.helloandroid.dagger.components.DaggerMockViewComponent
import dev.wizrad.helloandroid.dagger.modules.MockRiotServicesModule
import org.jetbrains.spek.api.Given
import org.jetbrains.spek.api.Spek
import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaPlugins
import rx.plugins.RxJavaSchedulersHook
import rx.schedulers.Schedulers

open abstract class Spec : Spek() {

  private object schedulersHook: RxJavaSchedulersHook() {
    var hasRegistered = false

    fun register() {
      if(!hasRegistered) {
        hasRegistered = true
        RxJavaPlugins.getInstance().registerSchedulersHook(this)
      }
    }

    override fun getIOScheduler(): Scheduler? {
      return Schedulers.immediate()
    }
  }

  init {
    schedulersHook.register()
  }

  // override given to register/deregister
  override fun given(description: String, expression: Given.() -> Unit) {
    super.given(description, {
      // create a scheduler hook that forces the immediate resolution
      val schedulerHook = object: RxAndroidSchedulersHook() {
          override fun getMainThreadScheduler(): Scheduler? {
            return Schedulers.immediate()
          }
      }

      // automatically register/reset the scheduler hook in before/after on
      beforeOn {
        RxAndroidPlugins.getInstance().registerSchedulersHook(schedulerHook)
      }

      afterOn {
        RxAndroidPlugins.getInstance().reset()
      }

      // then call the original expression on this
      expression()
    })
  }

  fun rootComponent(): DaggerMockRootComponent.Builder {
    return DaggerMockRootComponent.builder()
      .mockRiotServicesModule(MockRiotServicesModule())
  }

  fun viewComponent(): DaggerMockViewComponent.Builder {
    return DaggerMockViewComponent.builder()
      .mockRootComponent(rootComponent().build())
  }

}


