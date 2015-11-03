package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Nestable
import dev.wizrad.respek.dsl.Testable
import dev.wizrad.respek.exceptions.ContextFailureException
import dev.wizrad.respek.exceptions.HookFailureException
import dev.wizrad.respek.graph.interfaces.DebugPrintable
import dev.wizrad.respek.graph.interfaces.Parent
import dev.wizrad.respek.graph.interfaces.Traversable
import java.util.ArrayList

class Context(
  private val node: DslNode<Context>,
  private val parent: Parent? = null) : Nestable, Parent, Traversable, DebugPrintable {

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
  // Parent
  //

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
    val test = Test(node, this)
    tests.add(test)
    return test
  }

  override fun toString(): String {
    return node.message()
  }

  //
  // Traversable
  //

  override fun <T> reduce(initial: T, enumerator: (T, Test) -> Unit): T {
    return traverse(initial, { memo, context -> memo }, enumerator)
  }

  override fun <T> traverse(initial: T, nester: (T, Context) -> T, enumerator: (T, Test) -> Unit): T {
    val nested = nester(initial, this)

    for(test in tests) {
      enumerator(nested, test)
    }

    for(context in children) {
      context.traverse(nested, nester, enumerator)
    }

    return initial
  }

  //
  // Describable
  //

  override val description: String get() {
    return "${(parent?.description ?: "")} ${this.toString()}"
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
