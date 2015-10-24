package dev.wizrad.helloandroid.dagger.modules

import dagger.Module
import dagger.Provides
import dev.wizrad.helloandroid.presenters.MainPresenter
import dev.wizrad.helloandroid.presenters.MainPresenterType
import dev.wizrad.helloandroid.views.MainView

@Module
class MainModule(
  val view: MainView) {

  @Provides
  fun mainView(): MainView {
    return view
  }

  @Provides
  fun mainPresenter(presenter: MainPresenter): MainPresenterType {
    return presenter
  }
}
