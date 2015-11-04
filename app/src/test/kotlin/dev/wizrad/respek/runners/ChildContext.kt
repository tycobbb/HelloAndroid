package dev.wizrad.respek.runners

import dev.wizrad.respek.graph.Respek
import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Hooks
import org.junit.runner.Description
import org.junit.runner.notification.RunNotifier

class ChildContext<T: Respek>(
  val testClass: Class<T>,
  val context:   Context) : Child() {

  val children = lazy {
    context.map(
      { Child.context(testClass, it) },
      { Child.test(testClass, it) }
    )
  }

  //
  // Child
  //

  override fun action(notifier: RunNotifier) {
    context.willRun()

    for(child in children.value) {
      child.run(notifier)
    }

    context.didRun()
  }

  override val description: Description by lazy {
    Description
      .createSuiteDescription(context.description, id)
      .append(children.value.map { it.description })
  }

  //
  // Helpers
  //

  private fun Description.append(children: List<Description>) : Description {
    for(child in children) {
      this.addChild(child)
    }

    return this
  }
}
