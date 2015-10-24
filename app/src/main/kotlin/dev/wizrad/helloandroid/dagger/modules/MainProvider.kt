package dev.wizrad.helloandroid.dagger.modules

import dev.wizrad.helloandroid.presenters.MainPresenterType

interface MainProvider {
  fun mainPresenter(): MainPresenterType
}
