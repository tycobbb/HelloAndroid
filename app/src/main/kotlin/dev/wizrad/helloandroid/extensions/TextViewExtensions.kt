package dev.wizrad.helloandroid.extensions

import android.widget.TextView

import rx.Observable
import rx.Subscription

fun TextView.observeEnabled(source: Observable<Boolean>) : Subscription {
    return source.subscribe { isEnabled = it }
}
