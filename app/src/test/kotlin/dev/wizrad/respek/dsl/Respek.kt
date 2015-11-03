package dev.wizrad.respek.dsl

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.DslNode
import dev.wizrad.respek.graph.Test
import dev.wizrad.respek.graph.interfaces.Traversable
import dev.wizrad.respek.runners.SpecRunner
import org.junit.runner.RunWith

@RunWith(SpecRunner::class)
abstract class Respek() : Root, Traversable {

  private var context: Context? = null
  private val root: Context get() {
     if(context == null) {
      return context!! // TODO: obviously crashes, throw error if there's no root
    }

    return context!!
  }

  //
  // Definable
  //

  override fun given(message: String, expression: Nestable.() -> Unit) {
    if(context != null) {
      return // TODO: throw error if spec contains more than one root context
    } else {
      // construct the tree of nested contexts / tests starting this given context
      context = Context(DslNode.given(message, expression))
    }
  }

  //
  // Traversable
  //

  override fun <T> reduce(initial: T, enumerator: (T, Test) -> Unit): T {
    return root.reduce(initial, enumerator)
  }

  override fun <T> traverse(initial: T, nester: (T, Context) -> T, enumerator: (T, Test) -> Unit): T {
    return root.traverse(initial, nester, enumerator)
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
