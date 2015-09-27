package dev.wizrad.helloandroid

import dev.wizrad.helloandroid.core.Graph

public class MainApplication : BaseApplication() {

    public lateinit var graph: Graph private set

    override fun onCreate() {
        super.onCreate()

        this.graph = this.initGraph()
        this.graph.inject(this)
    }

}