package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.presenters.PresenterType
import dev.wizrad.helloandroid.dagger.components.DaggerActivityComponent

import android.app.Activity

public abstract class BaseActivity<P: PresenterType> : Activity() {

    abstract var presenter: P

    //
    // region Lifecycle
    //

    override fun onStart() {
        super.onStart()
        this.presenter.initialize()
    }

    // endregion

    //
    // region Injection
    //

    val component: DaggerActivityComponent.Builder get() {
        return DaggerActivityComponent.builder()
            .rootComponent((this.application as MainApplication).component);
    }

    // endregion

}