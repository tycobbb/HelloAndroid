package dev.wizrad.helloandroid.extensions

import android.content.Context
import android.widget.AbsSpinner
import android.widget.ArrayAdapter

import rx.Observable
import rx.Subscription

fun AbsSpinner.observe(
    source:   Observable<List<String>>,
    context:  Context,
    resource: Int = android.R.layout.simple_spinner_item) : Subscription {

    val adapter = ArrayAdapter<String>(context, resource)
    val subscription = source.subscribe { strings ->
        adapter.clear()
        adapter.addAll(strings)
    }

    this.adapter = adapter

    return subscription
}


