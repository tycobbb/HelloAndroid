package dev.wizrad.respek.dsl

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.DslNode
import dev.wizrad.respek.runners.SpecRunner
import org.junit.runner.RunWith

@RunWith(SpecRunner::class)
public abstract class Respek() : Root {

  private var root:  Context? = null

  fun run() {
    if(root == null) {
      return // TODO: throw error if there's no root
    } else {
      root!!.run()
    }
  }

  //
  // Definable
  //

  override fun given(message: String, expression: Nestable.() -> Unit) {
    if(root != null) {
      return // TODO: throw error if spec contains more than one root context
    } else {
      // construct the tree of nested contexts / tests starting this given context
      root = Context(DslNode.given(message, expression))
    }
  }

  //
  // Debugging
  //

  override fun toString(): String {
    var result = this.javaClass.name

    if(root != null) {
      result = "$result\n${root!!.debugString(0)}"
    }

    return result
  }
}