package dev.wizrad.helloandroid

import dev.wizrad.helloandroid.dagger.components.DaggerActivityComponent
import dev.wizrad.helloandroid.dagger.components.DaggerRootComponent
import dev.wizrad.helloandroid.dagger.components.RootComponent
import org.jetbrains.spek.api.Spek

open class Spec(body: Spec.() -> Unit = {}) : Spek() {
  init {
    this.body()
  }

  fun mockRootComponent() : RootComponent {
    return DaggerRootComponent.builder()
      .riotServicesModule(null).build()
  }

  fun mockViewComponent() : DaggerActivityComponent.Builder {
    return DaggerActivityComponent.builder()
      .rootComponent(mockRootComponent())
  }
}