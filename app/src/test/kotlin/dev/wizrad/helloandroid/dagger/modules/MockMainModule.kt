package dev.wizrad.helloandroid.dagger.modules

import dagger.Module
import dagger.Provides
import dev.wizrad.helloandroid.presenters.MainPresenter
import dev.wizrad.helloandroid.presenters.MainPresenterType
import dev.wizrad.helloandroid.views.MainView
import org.mockito.Mockito

@Module
class MockMainModule(
  val mocksPresenter: Boolean = true) {

  val view = lazy { Mockito.mock(MainView::class.java) }

  @Provides
  fun mainView(): MainView {
    return view.value
  }

  @Provides
  fun mainPresenter(presenter: MainPresenter): MainPresenterType {
    if(mocksPresenter) {
      return Mockito.mock(MainPresenterType::class.java)
    } else {
      return presenter
    }
  }
}
