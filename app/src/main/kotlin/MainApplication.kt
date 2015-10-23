package dev.wizrad.helloandroid

import dev.wizrad.helloandroid.dagger.components.RootComponent

internal class MainApplication : BaseApplication() {

    lateinit var component: RootComponent protected set

    override fun onCreate() {
        super.onCreate()

        component = this.initComponent()
        component.inject(this)
    }

}