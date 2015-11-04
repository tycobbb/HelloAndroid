package dev.wizrad.respek.graph.throwables

import dev.wizrad.respek.graph.Status
import dev.wizrad.respek.graph.Test

class StatusFailure(
  val test: Test,
  val status: Status) : Throwable() {

}
