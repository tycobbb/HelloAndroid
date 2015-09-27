package dev.wizrad.helloandroid

import dev.wizrad.helloandroid.dagger.components.RootComponent

public class MainApplication : BaseApplication() {

    public lateinit var component: RootComponent private set

    override fun onCreate() {
        super.onCreate()

        this.component = this.initComponent()
        this.component.inject(this)
    }

}