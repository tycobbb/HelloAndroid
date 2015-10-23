package dev.wizrad.helloandroid.extensions

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

internal fun ViewGroup.inflate(id: Int, context: Context = getContext(), attachToRoot: Boolean = true) : View {
    return context.inflate(id, this, attachToRoot)
}

internal fun Context.inflate(id: Int, parent: ViewGroup, attachToRoot: Boolean = true) : View {
    return LayoutInflater.from(this).inflate(id, parent, attachToRoot)
}
