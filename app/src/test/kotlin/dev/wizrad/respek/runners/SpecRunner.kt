package dev.wizrad.respek.runners

import dev.wizrad.respek.dsl.Respek
import org.junit.runner.Description
import org.junit.runner.notification.RunNotifier
import org.junit.runners.ParentRunner

class SpecRunner<T: Respek>(
  val testClass: Class<T>) : ParentRunner<Child>(testClass) {

  lateinit var spec: Respek

  init {
    try {
      spec = testClass.newInstance()
    } catch(exception: Exception) {
      print(exception)
    }
  }

  override fun getChildren(): MutableList<Child>? {
    return spec.map({ ChildContext(testClass, it) })
  }

  override fun describeChild(child: Child?): Description? {
    return child?.description
  }

  override fun runChild(child: Child?, notifier: RunNotifier?) {
    child?.run(notifier!!)
  }
}