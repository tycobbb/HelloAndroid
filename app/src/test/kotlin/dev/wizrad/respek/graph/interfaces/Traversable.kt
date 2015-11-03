package dev.wizrad.respek.graph.interfaces

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Test

interface Traversable {
  fun <T> reduce(initial: T, enumerator: (T, Test) -> Unit) : T
  fun <T> traverse(initial: T, nester: (T, Context) -> T, enumerator: (T, Test) -> Unit) : T
}