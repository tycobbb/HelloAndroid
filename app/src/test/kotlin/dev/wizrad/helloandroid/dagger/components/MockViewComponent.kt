package dev.wizrad.helloandroid.dagger.components

import dagger.Component
import dev.wizrad.helloandroid.dagger.modules.MainProvider
import dev.wizrad.helloandroid.dagger.modules.MockMainModule

@Component(modules = arrayOf(MockMainModule::class), dependencies = arrayOf(MockRootComponent::class))
interface MockViewComponent : MainProvider {

}
