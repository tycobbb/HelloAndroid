package dev.wizrad.helloandroid.core

import rx.Observable
import java.util.concurrent.TimeUnit

fun <T> later(value: T) : Observable<T> {
  return Observable.just(value).later()
}

fun <I, O> Observable<I>.later() : Observable<O> {
  return map { it as O }.delay(1, TimeUnit.SECONDS)
}


