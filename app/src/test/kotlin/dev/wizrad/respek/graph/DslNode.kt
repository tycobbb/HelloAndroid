package dev.wizrad.respek.graph

interface DslNode<T> {
  val status: Status

  fun message(): String
  fun action(executor: T)

  //
  // Factories
  //

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
        override val status: Status get() = Status.Normal
      }
    }

    fun test(message: String, expression: Test.() -> Unit, status: Status = Status.Normal) : DslNode<Test> {
      return object: DslNode<Test> {
        override fun message() = "it " + message
        override fun action(test: Test) = test.expression()
        override val status: Status get() = status
      }
    }
  }
}
