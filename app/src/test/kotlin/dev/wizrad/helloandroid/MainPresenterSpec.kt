package dev.wizrad.helloandroid

class MainPresenterSpec : Spec({

  val presenter = mockViewComponent()
    .mainModule(null).build()
    .mainPresenter()

  given("the main presenter") {
    on("construction")  {
      it("should exist") {
        assert(presenter != null)
      }
    }
  }

})
