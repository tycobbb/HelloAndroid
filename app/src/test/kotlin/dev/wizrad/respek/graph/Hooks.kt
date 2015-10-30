package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Hookable
import java.util.ArrayList

class Hooks : Hookable {

  enum class Type(val index: Int) {
    Before(0),
    BeforeEach(1),
    After(2),
    AfterEach(3)
  }

  private val hooks: Array<Hook> = Array(4, { Hook() })

  private operator fun Array<Hook>.get(type: Type) : Hook {
    return this[type.index]
  }

  //
  // Execution
  //

  fun run(type: Type) {
    hooks[type].run()
  }

  //
  // Hookable
  //

  override fun before(expression: () -> Unit) {
    this.add(Type.Before, expression)
  }

  override fun beforeEach(expression: () -> Unit) {
    this.add(Type.BeforeEach, expression)
  }

  override fun after(expression: () -> Unit) {
    this.add(Type.After, expression)
  }

  override fun afterEach(expression: () -> Unit) {
    this.add(Type.AfterEach, expression)
  }

  private fun add(type: Type, expression: () -> Unit) {
    hooks[type].add(expression)
  }

  //
  // Hook
  //

  private class Hook {
    private var closures: MutableList<() -> Unit> = ArrayList()

    fun add(closure: () -> Unit) {
      closures.add(closure)
    }

    fun run() {
      for(closure in closures) {
        closure()
      }
    }
  }

}