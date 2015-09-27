package dev.wizrad.helloandroid.dagger.components

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.dagger.modules.RiotServicesModule
import dev.wizrad.helloandroid.dagger.modules.RiotServicesProvider

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RiotServicesModule::class))
public interface RootComponent : RiotServicesProvider {
    public fun inject(application: MainApplication)
}
