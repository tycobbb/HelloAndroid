package dev.wizrad.respek.graph.throwables

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Hooks

internal class HookFailure(
  val hook:  Hooks.Type,
  context:   Context,
  throwable: Throwable) : ContextFailure(context, throwable) {

}