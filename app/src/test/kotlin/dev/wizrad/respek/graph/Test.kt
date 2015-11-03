package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Testable
import dev.wizrad.respek.exceptions.TestFailureException
import dev.wizrad.respek.graph.interfaces.DebugPrintable
import dev.wizrad.respek.graph.interfaces.Describable
import dev.wizrad.respek.graph.interfaces.Parent

class Test(
  private val node: DslNode<Test>,
  private val parent: Parent) : Testable, Describable, DebugPrintable {

  fun run() {
    parent.runHooks(Hooks.Type.BeforeEach)

    try {
      node.action(this)
    } catch(exception: Exception) {
      throw TestFailureException(this, exception)
    }

    parent.runHooks(Hooks.Type.AfterEach)
  }

  override fun toString(): String {
    return node.message()
  }

  //
  // Describable
  //

  override val description: String get() {
    return "${parent.description} ${this.toString()}"
  }

  //
  // DebugPrintable
  //

  override fun debugString(depth: Int): String {
    return this.paddedString(depth)
  }
}
