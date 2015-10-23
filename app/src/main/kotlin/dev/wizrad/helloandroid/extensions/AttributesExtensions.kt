package dev.wizrad.helloandroid.extensions

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.AttrRes
import android.support.annotation.StyleableRes
import android.util.AttributeSet

internal fun AttributeSet.unwrap(@StyleableRes resources: IntArray, context: Context) : TypedArray {
    return context.obtainStyledAttributes(this, resources)
}

//
// Subscripting
//

@Suppress("UNCHECKED_CAST")
internal operator fun <T: Any> TypedArray.get(@AttrRes index: Int) : T? {
    var result: T? = null

    if(result is CharSequence?) {
        result = this.getText(index) as? T
    } else if(result is Boolean?) {
        result = this.getBoolean(index, false) as? T
    } else {
        assert(false) { "subscripting on TypedArray does not support parameters of this type" }
    }

    return result
}
