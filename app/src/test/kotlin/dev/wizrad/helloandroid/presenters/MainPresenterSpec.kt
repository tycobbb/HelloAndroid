package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.core.viewComponent
import dev.wizrad.helloandroid.dagger.modules.MockMainModule
import dev.wizrad.helloandroid.models.Region
import dev.wizrad.helloandroid.views.MainView
import org.jetbrains.spek.api.Spek
import org.mockito.Mockito.*
import rx.Observable

class MainPresenterSpec : Spek() { init {

  given("the main presenter") {
    var module:  MockMainModule
    var subject: MainPresenterType

    fun view() : MainView {
      return module.view.value
    }

    beforeOn {
      module  = MockMainModule(false)
      subject = viewComponent()
        .mockMainModule(module).build()
        .mainPresenter()
    }

    on("on did become active") {
      `when`(view().summonerName()).thenReturn(Observable.just("fartbutt"))
      `when`(view().selectedRegion()).thenReturn(Observable.just(1))
      `when`(view().action()).thenReturn(Observable.just(1))

      subject.becomeActive()

      it("should update the view with the list of regions") {
        verify(module.view.value).didUpdateRegions(Region.all().map { it.code })
      }

      it("should default the selected region to North America") {
        verify(module.view.value).didUpdateSelectedRegion(Region.NA.code)
      }
    }
  }

}}
