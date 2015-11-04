package dev.wizrad.respek.graph.throwables

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Hooks

internal class HookFailure(
  val hook: Hooks.Type,
  context: Context,
  exception: Exception) : ContextFailure(context, exception) {

}