package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Testable
import dev.wizrad.respek.graph.throwables.TestFailure
import dev.wizrad.respek.graph.interfaces.DebugPrintable
import dev.wizrad.respek.graph.interfaces.Describable
import dev.wizrad.respek.graph.interfaces.Parent
import dev.wizrad.respek.graph.throwables.StatusFailure

class Test(
  private val node: DslNode<Test>,
  private val parent: Parent) : Testable, Describable, DebugPrintable {

  fun run() {
    if(node.status != Status.Normal) {
      throw StatusFailure(this, node.status)
    }

    parent.runHooks(Hooks.Type.BeforeEach)

    try {
      node.action(this)
    } catch(failure: Throwable) {
      throw TestFailure(this, failure)
    }

    parent.runHooks(Hooks.Type.AfterEach)
  }


  //
  // Describable
  //

  override val description: String get() {
    return node.message()
  }

  override fun toString(): String {
    return node.message()
  }

  //
  // DebugPrintable
  //

  override fun debugString(depth: Int): String {
    return this.paddedString(depth)
  }
}
