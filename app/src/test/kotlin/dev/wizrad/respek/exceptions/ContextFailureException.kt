package dev.wizrad.respek.exceptions

import dev.wizrad.respek.graph.Context

open internal class ContextFailureException(
  val context: Context,
  val exception: Exception) : Exception() {

}