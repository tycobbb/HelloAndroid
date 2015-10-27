package dev.wizrad.helloandroid.dagger.components

import dagger.Component
import dev.wizrad.helloandroid.dagger.modules.MockRiotServicesModule
import dev.wizrad.helloandroid.dagger.modules.RiotServicesProvider

@Component(modules = arrayOf(MockRiotServicesModule::class))
interface MockRootComponent : RiotServicesProvider {

}
