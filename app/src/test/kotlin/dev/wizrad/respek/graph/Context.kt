package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Nestable
import dev.wizrad.respek.dsl.Testable
import dev.wizrad.respek.exceptions.ContextFailureException
import dev.wizrad.respek.exceptions.HookFailureException
import dev.wizrad.respek.exceptions.TestFailureException
import java.util.ArrayList

internal class Context(
  private val node: DslNode<Context>,
  private val parent: Runnable? = null) : Nestable, Runnable, DebugPrintable {

  private val hooks = Hooks()
  private val children: MutableList<Context> = ArrayList()
  private val tests:    MutableList<Test>    = ArrayList()

  init {
    try {
      node.action(this)
    } catch(exception: Exception) {
      throw ContextFailureException(this, exception)
    }
  }

  //
  // Runnable
  //

  override fun run() {
    this.runOwnHooks(Hooks.Type.Before)

    // run all our tests, calling the appropriate hooks
    for(test in tests) {
      this.runHooks(Hooks.Type.BeforeEach)
      this.runTest(test)
      this.runHooks(Hooks.Type.AfterEach)
    }

    // run all our child contexts
    for(context in children) {
      context.run()
    }

    this.runOwnHooks(Hooks.Type.After)
  }

  override fun runHooks(type: Hooks.Type) {
    parent?.runHooks(type)
    this.runOwnHooks(type)
  }

  private fun runOwnHooks(type: Hooks.Type) {
    try {
      hooks.run(type)
    } catch(exception: Exception) {
      throw HookFailureException(type, this, exception)
    }
  }

  private fun runTest(test: Test) {
    try {
      test.run()
    } catch(exception: Exception) {
      throw TestFailureException(test, this, exception)
    }
  }

  //
  // Nesting
  //

  override fun given(message: String, expression: Nestable.() -> Unit) {
    this.appendContext(DslNode.given(message, expression))
  }

  override fun on(message: String, expression: Nestable.() -> Unit) {
    this.appendContext(DslNode.on(message, expression))
  }

  private fun appendContext(description: DslNode<Context>) {
    children.add(Context(description, this))
  }

  //
  // Hookable
  //

  override fun before(expression: () -> Unit) = hooks.before(expression)
  override fun beforeEach(expression: () -> Unit) = hooks.beforeEach(expression)
  override fun after(expression: () -> Unit)  = hooks.after(expression)
  override fun afterEach(expression: () -> Unit) = hooks.afterEach(expression)

  //
  // Tests
  //

  override fun it(message: String, expression: Testable.() -> Unit) {
    this.appendTest(DslNode.test(message, expression))
  }

  override fun xit(message: String, expression: Testable.() -> Unit) {
    this.appendTest(DslNode.test(message, expression))
  }

  private fun appendTest(node: DslNode<Test>) : Test {
    val test = Test(node)
    tests.add(test)
    return test
  }

  override fun toString(): String {
    return node.message()
  }

  //
  // DebugPrintable
  //

  override fun debugString(depth: Int): String {
    var result = "${this.paddedString(depth)}\n"

    for(test in tests) {
      result += "${test.debugString(depth + 1)}\n"
    }

    for(context in children) {
      result += "${context.debugString(depth + 1)}"
    }

    return result
  }
}
