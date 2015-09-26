package dev.wizrad.helloandroid

public class MainApplication : BaseApplication() {

    public lateinit var component: Graph
        private set

    override fun onCreate() {
        super.onCreate()

        val component = this.initComponent()
        component?.inject(this)
        this.component = component!!
    }

}