package dev.wizrad.helloandroid

public class MainApplication : BaseApplication() {

    public lateinit var graph: Graph private set

    override fun onCreate() {
        super.onCreate()

        val graph = this.initGraph()
        graph.inject(this)
        this.graph = graph
    }

}