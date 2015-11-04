package dev.wizrad.respek.graph.throwables

import dev.wizrad.respek.graph.Test

internal class TestFailure(
  val test: Test,
  val exception: Exception) : Throwable() {

}
