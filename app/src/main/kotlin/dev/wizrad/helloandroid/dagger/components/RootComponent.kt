package dev.wizrad.helloandroid.dagger.components

import dagger.Component
import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.dagger.modules.RiotServicesModule
import dev.wizrad.helloandroid.dagger.modules.RiotServicesProvider
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RiotServicesModule::class))
interface RootComponent : RiotServicesProvider {
  fun inject(application: MainApplication)
}
