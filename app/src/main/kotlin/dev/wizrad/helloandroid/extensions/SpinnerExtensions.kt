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

    val localAdapter = ArrayAdapter<String>(context, resource)
    val subscription = source.subscribe { strings ->
        localAdapter.clear()
        localAdapter.addAll(strings)
    }

    adapter = localAdapter

    return subscription
}


