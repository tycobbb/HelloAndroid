package dev.wizrad.respek.runners

import dev.wizrad.respek.graph.Respek
import dev.wizrad.respek.graph.Test
import dev.wizrad.respek.graph.throwables.StatusFailure
import org.junit.runner.Description
import org.junit.runner.notification.RunNotifier

class ChildTest<T: Respek>(
  val testClass: Class<T>,
  val test: Test) : Child() {

  override fun action(notifier: RunNotifier) {
    test.run()
  }

  override val description: Description by lazy {
    Description.createTestDescription(testClass.name, test.description, id)
  }
}
