package dev.wizrad.respek.runners

import dev.wizrad.respek.dsl.Respek
import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier

public class SpecRunner<T: Respek>() : Runner() {

  lateinit var spec: Respek

  constructor(klass: Class<T>) : this() {
    try {
      spec = klass.newInstance()
      val desc = spec.toString()
      print(desc)
    } catch(exception: Exception) {
      print(exception)
    }
  }

  override fun run(notifier: RunNotifier?) {

  }

  override fun testCount(): Int {
    return 0
  }

  override fun getDescription(): Description? {
    return Description.TEST_MECHANISM
  }

}