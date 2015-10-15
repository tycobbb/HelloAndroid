package dev.wizrad.helloandroid.extensions

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun Context.inflate(id: Int, parent: ViewGroup, attachToRoot: Boolean = true) : View {
    return LayoutInflater.from(this).inflate(id, parent, attachToRoot)
}
