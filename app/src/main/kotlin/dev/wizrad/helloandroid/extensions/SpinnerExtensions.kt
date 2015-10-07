package dev.wizrad.helloandroid.extensions

import android.content.Context
import android.widget.AbsSpinner
import android.widget.ArrayAdapter

val defaultResource = android.R.layout.simple_spinner_item

fun <T> AbsSpinner.setItems(items: Collection<T>?, context: Context, resource: Int = defaultResource)  {
    val adapter = ArrayAdapter<T>(context, resource)
    adapter.addAll(items)
    this.adapter = adapter
}
