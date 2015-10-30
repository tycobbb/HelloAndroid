package dev.wizrad.respek.graph

import dev.wizrad.respek.dsl.Hookable

public interface Runnable : Hookable {
  fun run()
  fun runHooks(type: Hooks.Type)
}