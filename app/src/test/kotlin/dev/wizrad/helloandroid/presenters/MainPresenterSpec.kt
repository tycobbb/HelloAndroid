package dev.wizrad.helloandroid.presenters

import dev.wizrad.helloandroid.core.Spec
import dev.wizrad.helloandroid.core.later
import dev.wizrad.helloandroid.dagger.modules.MockMainModule
import dev.wizrad.helloandroid.models.Region
import dev.wizrad.helloandroid.views.MainView
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.expect

class MainPresenterSpec : Spec() { init {

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

    on("becoming active") {
      `when`(view().summonerName()).thenReturn(later("fartbutt"))
      `when`(view().selectedRegion()).thenReturn(later(1))
      `when`(view().action()).thenReturn(later(1))

      subject.becomeActive()

      it("should update the view with the list of regions") {
        verify(view()).didUpdateRegions(Region.all().map { it.code })
      }

      it("should default the selected region to North America") {
        verify(view()).didUpdateSelectedRegion(Region.NA.code)
      }

      it("should the default the submit button to disabled") {
        verify(view()).didEnableSubmit(false)
      }
    }
  }

}}
