package dev.wizrad.respek.exceptions

import dev.wizrad.respek.graph.Test

internal class TestFailureException(
  val test: Test,
  val exception: Exception) : Exception() {

}
