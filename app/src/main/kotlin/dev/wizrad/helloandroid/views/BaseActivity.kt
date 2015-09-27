package dev.wizrad.helloandroid.views

import dev.wizrad.helloandroid.MainApplication
import dev.wizrad.helloandroid.dagger.components.DaggerActivityComponent

import android.app.Activity

public abstract class BaseActivity : Activity() {

    val component: DaggerActivityComponent.Builder get() {
        return DaggerActivityComponent.builder()
            .rootComponent((this.application as MainApplication).component);
    }

}