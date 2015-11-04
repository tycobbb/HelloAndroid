package dev.wizrad.respek.dsl

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.DslNode
import dev.wizrad.respek.graph.Test
import dev.wizrad.respek.graph.interfaces.Mappable
import dev.wizrad.respek.runners.SpecRunner
import org.junit.runner.RunWith

@RunWith(SpecRunner::class)
abstract class Respek() : Root, Mappable {

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
  // Mappable
  //

  override fun <T> map(contextTransform: ((Context) -> T)?, testTransform: ((Test) -> T)?): MutableList<T> {
    return arrayListOf(contextTransform!!(root))
  }

  //
  // Debugging
  //

  override fun toString(): String {
    var result = this.javaClass.name

    if(context != null) {
      result = "$result\n${root.debugString(0)}"
    }

    return result
  }
}
