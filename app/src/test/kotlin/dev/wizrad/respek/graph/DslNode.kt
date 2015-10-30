package dev.wizrad.respek.graph

import dev.wizrad.respek.graph.Test
import dev.wizrad.respek.graph.Context

internal interface DslNode<T> {
  fun message(): String
  fun action(executor: T)

  companion object {
    fun on(message: String, expression: Context.() -> Unit) : DslNode<Context> {
      return prefixed("on", message, expression)
    }

    fun given(message: String, expression: Context.() -> Unit) : DslNode<Context> {
      return prefixed("given", message, expression)
    }

    fun prefixed(prefix: String, message: String, expression: Context.() -> Unit) : DslNode<Context> {
      return object: DslNode<Context> {
        override fun message() = prefix + " " + message
        override fun action(context: Context) = context.expression()
      }
    }

    fun test(message: String, expression: Test.() -> Unit) : DslNode<Test> {
      return object: DslNode<Test> {
        override fun message() = "it " + message
        override fun action(test: Test) = test.expression()
      }
    }
  }
}
