package dev.wizrad.respek.exceptions

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Test

internal class TestFailureException(
  val test: Test,
  context: Context,
  exception: Exception) : ContextFailureException(context, exception) {

}
