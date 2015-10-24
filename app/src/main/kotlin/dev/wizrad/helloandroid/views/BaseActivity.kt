package dev.wizrad.helloandroid.views

import android.app.Activity
import android.os.Bundle
import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.dagger.components.DaggerActivityComponent
import dev.wizrad.helloandroid.presenters.PresenterType
import javax.inject.Inject

abstract class BaseActivity<P : PresenterType> : Activity() {

  //
  // Dependencies
  //

  protected lateinit var presenter: P @Inject set

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

    // and allow the present to set-up
    presenter.becomeActive()
  }

  protected override fun onStop() {
    super.onStop()

    // allow the presenter to clean up
    presenter.resignActive()
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