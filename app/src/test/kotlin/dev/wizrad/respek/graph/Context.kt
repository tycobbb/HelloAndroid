package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Nestable
import dev.wizrad.respek.dsl.Testable
import dev.wizrad.respek.graph.throwables.ContextFailure
import dev.wizrad.respek.graph.throwables.HookFailure
import dev.wizrad.respek.graph.interfaces.DebugPrintable
import dev.wizrad.respek.graph.interfaces.Parent
import dev.wizrad.respek.graph.interfaces.Mappable
import dev.wizrad.respek.utilities.unwrap
import java.util.ArrayList

class Context(
  private val node: DslNode<Context>,
  private val parent: Parent? = null) : Nestable, Parent, Mappable, DebugPrintable {

  private val hooks = Hooks()
  private val children: MutableList<Context> = ArrayList()
  private val tests:    MutableList<Test>    = ArrayList()

  init {
    try {
      node.action(this)
    } catch(exception: Exception) {
      throw ContextFailure(this, exception)
    }
  }

  fun willRun() {
    this.runOwnHooks(Hooks.Type.Before)
  }

  fun didRun() {
    this.runOwnHooks(Hooks.Type.After)
  }

  //
  // Parent
  //

  override fun runHooks(type: Hooks.Type) {
    parent?.runHooks(type)
    this.runOwnHooks(type)
  }

  fun runOwnHooks(type: Hooks.Type) {
    try {
      hooks.run(type)
    } catch(exception: Exception) {
      throw HookFailure(type, this, exception)
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
  // Tests
  //

  override fun it(message: String, expression: Testable.() -> Unit) {
    this.appendTest(DslNode.test(message, expression))
  }

  override fun xit(message: String, expression: Testable.() -> Unit) {
    this.appendTest(DslNode.test(message, expression, Status.Skipped))
  }

  private fun appendTest(node: DslNode<Test>) : Test {
    val test = Test(node, this)
    tests.add(test)
    return test
  }

  //
  // Hookable
  //

  override fun before(expression: () -> Unit) = hooks.before(expression)
  override fun beforeEach(expression: () -> Unit) = hooks.beforeEach(expression)
  override fun after(expression: () -> Unit)  = hooks.after(expression)
  override fun afterEach(expression: () -> Unit) = hooks.afterEach(expression)

  //
  // Mappable
  //

  override fun <T> map(contextTransform: ((Context) -> T)?, testTransform: ((Test) -> T)?): MutableList<T> {
    val result = arrayListOf<T>()

    testTransform.unwrap { transform ->
      for (test in tests) {
        result.add(transform(test))
      }
    }

    contextTransform.unwrap { transform ->
      for(context in children) {
        result.add(transform(context))
      }
    }

    return result
  }

  //
  // Describable
  //

  override val description: String get() {
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

  override fun toString(): String {
    return node.message()
  }
}
