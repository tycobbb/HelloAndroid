package dev.wizrad.respek.exceptions

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Hooks

internal class HookFailureException(
  val hookType: Hooks.Type,
  context: Context,
  exception: Exception) : ContextFailureException(context, exception) {

}