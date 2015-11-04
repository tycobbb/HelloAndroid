package dev.wizrad.respek.graph.throwables

import dev.wizrad.respek.graph.Context

open internal class ContextFailure(
  val context: Context,
  val exception: Exception) : Throwable(cause = exception) {

}