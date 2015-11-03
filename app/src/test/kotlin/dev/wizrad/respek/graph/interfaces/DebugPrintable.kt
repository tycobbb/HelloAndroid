package dev.wizrad.respek.graph.interfaces

interface DebugPrintable {
  fun debugString(depth: Int) : String

  fun paddedString(depth: Int) : String {
    return " ".repeat(depth * 2) + this.toString()
  }
}