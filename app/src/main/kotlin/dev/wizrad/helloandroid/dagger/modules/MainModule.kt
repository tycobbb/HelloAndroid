package dev.wizrad.helloandroid.dagger.modules

import dev.wizrad.helloandroid.views.MainView
import dev.wizrad.helloandroid.presenters.MainPresenter
import dev.wizrad.helloandroid.presenters.MainPresenterType

import dagger.Module
import dagger.Provides

@Module
public class MainModule(
    val view: MainView) {

    @Provides
    fun mainView() : MainView {
        return this.view
    }

    @Provides
    fun mainPresenter(presenter: MainPresenter) : MainPresenterType {
        return presenter
    }

}
