package dev.wizrad.helloandroid

import dev.wizrad.helloandroid.dagger.components.RootComponent
import dev.wizrad.helloandroid.dagger.components.DaggerRootComponent
import dev.wizrad.helloandroid.dagger.modules.RiotServicesModule

import android.app.Application

public abstract class BaseApplication : Application() {

    protected fun initComponent() : RootComponent {
        return DaggerRootComponent.builder()
            .riotServicesModule(RiotServicesModule())
            .build()
    }

}