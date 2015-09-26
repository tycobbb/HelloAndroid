package dev.wizrad.helloandroid

import dev.wizrad.helloandroid.services.modules.RiotServicesModule
import android.app.Application

public abstract class BaseApplication : Application() {

    protected fun initGraph() : Graph {
        return DaggerGraph.builder()
            .riotServicesModule(RiotServicesModule(this))
            .build()
    }

}