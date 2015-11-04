package dev.wizrad.respek.runners

import dev.wizrad.respek.graph.Respek
import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Test
import dev.wizrad.respek.graph.throwables.HookFailure
import dev.wizrad.respek.graph.throwables.StatusFailure
import dev.wizrad.respek.graph.throwables.TestFailure
import org.junit.runner.Description
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import java.io.Serializable

abstract class Child {

  protected val id: Id = Id.next()

  abstract val description: Description
  abstract fun action(notifier: RunNotifier)

  //
  // execution
  //

  fun run(notifier: RunNotifier) {
    if(description.isTest) {
      notifier.fireTestStarted(description)
    }

    try {
      action(notifier)
    } catch(failure: HookFailure) {
      // not sure what to do here
    } catch(failure: TestFailure) {
      notifier.fireTestFailure(Failure(description, failure))
    } catch(failure: StatusFailure) {
      notifier.fireTestIgnored(description)
    } finally {
      if(description.isTest) {
        notifier.fireTestFinished(description)
      }
    }
  }

  //
  // factory
  //

  companion object {
    fun <T: Respek> context(testClass: Class<T>, context: Context): ChildContext<T> {
      return ChildContext(testClass, context)
    }

    fun <T: Respek> test(testClass: Class<T>, test: Test): ChildTest<T> {
      return ChildTest(testClass, test)
    }
  }

  //
  // id
  //

  protected data class Id private constructor(
    val value: Int) : Serializable {

    companion object {
      private var id = 0
      fun next() = Id(id++)
    }
  }
}
