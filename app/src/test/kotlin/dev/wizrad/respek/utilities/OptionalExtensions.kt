package dev.wizrad.respek.utilities

fun <T> T?.unwrap(closure: (T) -> Unit)  {
  if(this != null) {
    closure(this)
  }
}
