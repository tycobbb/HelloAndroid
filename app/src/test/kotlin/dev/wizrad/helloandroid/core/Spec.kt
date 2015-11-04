package dev.wizrad.helloandroid.core

import dev.wizrad.helloandroid.dagger.components.DaggerMockRootComponent
import dev.wizrad.helloandroid.dagger.components.DaggerMockViewComponent
import dev.wizrad.helloandroid.dagger.modules.MockRiotServicesModule
import dev.wizrad.respek.dsl.Nestable
import dev.wizrad.respek.graph.Respek
import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaPlugins
import rx.plugins.RxJavaSchedulersHook
import rx.schedulers.Schedulers

abstract class Spec : Respek() {

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

  override fun given(message: String, expression: Nestable.() -> Unit) {
    super.given(message, {
      // create a scheduler hook that forces the immediate resolution
      val schedulersHook = object: RxAndroidSchedulersHook() {
          override fun getMainThreadScheduler(): Scheduler? {
            return Schedulers.immediate()
          }
      }

      // automatically register/reset the scheduler hook in before/after on
      beforeEach {
        RxAndroidPlugins.getInstance().registerSchedulersHook(schedulersHook)
      }

      afterEach {
        RxAndroidPlugins.getInstance().reset()
      }

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


