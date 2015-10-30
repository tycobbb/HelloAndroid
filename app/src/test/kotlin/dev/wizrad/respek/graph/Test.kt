package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Testable
import dev.wizrad.respek.exceptions.TestFailureException
import dev.wizrad.respek.graph.DebugPrintable
import dev.wizrad.respek.graph.DslNode
import junit.framework.TestFailure

internal class Test(
  private val node: DslNode<Test>) : Testable, DebugPrintable {

  fun run() {
    node.action(this)
  }

  override fun debugString(depth: Int): String {
    return node.message().padStart(depth * 2)
  }
}
