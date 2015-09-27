package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.core.Graph
import dev.wizrad.helloandroid.core.Graphable

import android.app.Activity

public abstract class BaseActivity : Activity(), Graphable {

    //
    // region Graphable
    //

    override val graph: Graph get() {
        return (this.application as MainApplication).graph
    }

    // endregion

}