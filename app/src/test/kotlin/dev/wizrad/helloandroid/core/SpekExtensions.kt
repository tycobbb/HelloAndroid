package dev.wizrad.helloandroid.core

import dev.wizrad.helloandroid.dagger.components.DaggerMockRootComponent
import dev.wizrad.helloandroid.dagger.components.DaggerMockViewComponent
import dev.wizrad.helloandroid.dagger.modules.MockRiotServicesModule
import org.jetbrains.spek.api.Spek

fun Spek.rootComponent(): DaggerMockRootComponent.Builder {
  return DaggerMockRootComponent.builder()
    .mockRiotServicesModule(MockRiotServicesModule())
}

fun Spek.viewComponent(): DaggerMockViewComponent.Builder {
  return DaggerMockViewComponent.builder()
    .mockRootComponent(rootComponent().build())
}

