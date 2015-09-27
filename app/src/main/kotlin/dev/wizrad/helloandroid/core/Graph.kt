package dev.wizrad.helloandroid.core

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.views.MainActivity
import dev.wizrad.helloandroid.services.modules.RiotServicesModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RiotServicesModule::class))
public interface Graph {

    public fun inject(application: MainApplication)
    public fun inject(mainActivity: MainActivity)

}

