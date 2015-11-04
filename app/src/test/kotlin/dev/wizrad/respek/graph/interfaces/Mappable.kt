package dev.wizrad.respek.graph.interfaces

import dev.wizrad.respek.graph.Context
import dev.wizrad.respek.graph.Test

interface Mappable {
  fun <T> map(contextTransform: ((Context) -> T)? = null, testTransform: ((Test) -> T)? = null) : MutableList<T>
}