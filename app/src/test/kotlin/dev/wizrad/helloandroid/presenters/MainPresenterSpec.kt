package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.core.Spec
import dev.wizrad.helloandroid.core.later
import dev.wizrad.helloandroid.dagger.modules.MockMainModule
import dev.wizrad.helloandroid.models.Region
import dev.wizrad.helloandroid.views.MainView
import org.mockito.Mockito.*
import rx.subjects.PublishSubject

class MainPresenterSpec : Spec() { init {

  given("the main presenter") {
    var module:  MockMainModule
    var subject: MainPresenterType

    fun view() : MainView {
      return module.view.value
    }

    beforeEach {
      module  = MockMainModule(false)
      subject = viewComponent()
        .mockMainModule(module).build()
        .mainPresenter()
    }

    afterEach {
      subject.resignActive()
    }

    on("becoming active") {
      beforeEach {
        `when`(view().summonerName()).thenReturn(later("fartbutt"))
        `when`(view().selectedRegion()).thenReturn(later(1))
        `when`(view().action()).thenReturn(later(1))

        subject.becomeActive()
      }

      it("should update the view with the list of regions") {
        verify(view()).didUpdateRegions(Region.all().map { it.code })
      }

      it("should default the selected region to North America") {
        verify(view()).didUpdateSelectedRegion(Region.NA.code)
      }

      it("should default submission to disabled") {
        verify(view()).didEnableSubmit(false)
      }
    }

    on("entering a name") {
      var name: PublishSubject<CharSequence>

      beforeEach {
        name = PublishSubject.create<CharSequence>()

        `when`(view().summonerName()).thenReturn(name.asObservable())
        `when`(view().selectedRegion()).thenReturn(later(1))
        `when`(view().action()).thenReturn(later(1))

        subject.becomeActive()
      }

      xit("should enable submission") {
        name.onNext("fartbutt")
        verify(view()).didEnableSubmit(true)
      }
    }
  }
}}
