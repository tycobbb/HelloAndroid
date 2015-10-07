package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.utilities.Subscriptions
import rx.Subscription
import java.util.ArrayList

internal abstract class Presenter : PresenterType {

    //
    // region Properties
    //

    private var _subscriptions: MutableList<Subscription>? = null

    override val isActive: Boolean get() {
        return this._subscriptions != null
    }

    override val subscriptions: Subscriptions get() {
        return Subscriptions(this._subscriptions!!)
    }

    // endregion

    //
    // region Lifecycle
    //

    override fun initialize() {

    }

    override fun becomeActive() {
        this._subscriptions = ArrayList<Subscription>()

        this.didBecomeActive()
    }

    protected open fun didBecomeActive() {

    }

    override fun resignActive() {
        val subscriptions   = this._subscriptions!!
        this._subscriptions = null

        for(subscription in subscriptions) {
            subscription.unsubscribe()
        }

        this.didResignActive()
    }

    protected open fun didResignActive() {

    }

    // endregion

}
