package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.presenters.PresenterType
import dev.wizrad.helloandroid.dagger.components.DaggerActivityComponent

import android.app.Activity
import javax.inject.Inject

public abstract class BaseActivity<P: PresenterType> : Activity() {

    //
    // region Dependencies
    //

    protected lateinit var presenter: P @Inject set

    // endregion

    //
    // region Lifecycle
    //

    protected override fun onStart() {
        super.onStart()
        this.presenter.initialize()
    }

    // endregion

    //
    // region Injection
    //

    protected val component: DaggerActivityComponent.Builder get() {
        return DaggerActivityComponent.builder()
            .rootComponent((this.application as MainApplication).component);
    }

    // endregion

}