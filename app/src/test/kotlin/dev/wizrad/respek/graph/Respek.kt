package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Nestable
import dev.wizrad.respek.dsl.Root
import dev.wizrad.respek.graph.interfaces.Mappable
import dev.wizrad.respek.graph.throwables.SpecException
import dev.wizrad.respek.runners.SpecRunner
import org.junit.runner.RunWith

@RunWith(SpecRunner::class)
abstract class Respek() : Root, Mappable {

  private var context: Context? = null
  private val root: Context get() {
    if(context == null) {
      throw SpecException("Spec ${javaClass.name} did not contain a `given` block")
    }

    return context!!
  }

  //
  // Definable
  //

  override fun given(message: String, expression: Nestable.() -> Unit) {
    if(context != null) {
      throw SpecException("Spec ${javaClass.name} can only contain one `given` block at the root level")
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
