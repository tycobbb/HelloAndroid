package dev.wizrad.respek.runners

import dev.wizrad.respek.dsl.Respek
import dev.wizrad.respek.graph.Test
import org.junit.runner.Description
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.junit.runners.ParentRunner
import java.io.Serializable

class SpecRunner<T: Respek>(klass: Class<T>) : ParentRunner<Test>(klass) {

  var spec: Respek? = null

  init {
    try {
      spec = klass.newInstance()
    } catch(exception: Exception) {
      print(exception)
    }
  }

  override fun getChildren(): MutableList<Test>? {
    return spec?.reduce(arrayListOf<Test>()) { memo, test ->
      memo.add(test)
    }
  }

  override fun describeChild(child: Test?): Description? {
    return Description.createTestDescription(spec?.javaClass?.name, child?.description)
  }

  override fun runChild(child: Test?, notifier: RunNotifier?) {
    var failure : Throwable? = null
    val description = this.describeChild(child)

    notifier?.fireTestStarted(description)

    try {
      child?.run()
    } catch(error: Throwable) {
      failure = error
    } finally {
      if(failure != null) {
        notifier?.fireTestFailure(Failure(description, failure))
      } else {
        notifier?.fireTestFinished(description)
      }
    }
  }

  //
  // Unique Id
  //

  data class Id private constructor(val value: Int) : Serializable {
    companion object {
      private var last: Int = 0
      fun next(): Id = Id(++last)
    }
  }

}