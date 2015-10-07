package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.presenters.PresenterType
import dev.wizrad.helloandroid.dagger.components.DaggerActivityComponent

import android.app.Activity
import android.os.Bundle
import dev.wizrad.helloandroid.utilities.Subscriptions
import rx.Subscription
import java.util.*
import javax.inject.Inject

public abstract class BaseActivity<P: PresenterType> : Activity() {

    //
    // Dependencies
    //

    protected lateinit var presenter: P @Inject set

    //
    // Properties
    //

    private var _subscriptions: MutableList<Subscription>? = null

    val subscriptions: Subscriptions get() {
        return Subscriptions(_subscriptions!!)
    }

    //
    // Lifecycle
    //

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inject dependencies before base onCreate finishes and before the
        // presenter is referenced
        this.onInject()

        presenter.initialize()
    }

    protected override fun onStart() {
        super.onStart()

        // provide storage for our subscriptions
        _subscriptions = ArrayList<Subscription>()
        // and allow the present to set-up
        presenter.becomeActive()
    }

    protected override fun onStop() {
        super.onStop()

        // allow the presenter to clean up
        presenter.resignActive()

        // then clean up our subscriptions
        val subscriptions = _subscriptions
        _subscriptions    = null

        for(subscription in subscriptions!!) {
            subscription.unsubscribe()
        }
    }

    //
    // region Injection
    //

    abstract fun onInject()

    protected val component: DaggerActivityComponent.Builder get() {
        return DaggerActivityComponent.builder()
            .rootComponent((application as MainApplication).component);
    }

}