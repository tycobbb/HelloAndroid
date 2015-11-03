package dev.wizrad.respek.graph.interfaces

import dev.wizrad.respek.graph.Hooks

interface Parent : Describable {
  fun runHooks(type: Hooks.Type)
}
