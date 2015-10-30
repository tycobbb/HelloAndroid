package dev.wizrad.respek.dsl

//
// Elements
//

interface Root : Definable {

}

interface Nestable : Definable, Hookable {
  fun on(message: String, expression: Nestable.() -> Unit)
  fun it(message: String, expression: Testable.() -> Unit)
  fun xit(message: String, expression: Testable.() -> Unit)
}

interface Testable {

}

//
// Aspects
//

interface Definable {
  fun given(message: String, expression: Nestable.() -> Unit)
}

interface Hookable {
  fun before(expression: () -> Unit)
  fun after(expression: () -> Unit)
  fun beforeEach(expression: () -> Unit)
  fun afterEach(expression: () -> Unit)
}
