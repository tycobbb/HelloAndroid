package dev.wizrad.helloandroid.views.shared

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.bindView
import dev.wizrad.helloandroid.R
import dev.wizrad.helloandroid.extensions.inflate
import dev.wizrad.helloandroid.extensions.unwrap
import dev.wizrad.helloandroid.extensions.get

internal class FormFieldLayout : LinearLayout {

    val titleLabel: TextView by bindView(R.id.form_field_label)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defaultStyle: Int)
        : super(context, attrs, defaultStyle) {

        inflate(R.layout.view_form_field_layout, context)

        // configure layout
        orientation = LinearLayout.VERTICAL

        // apply optional styleable attributes
        val attributes = attrs.unwrap(R.styleable.FormFieldLayout, context)
        titleLabel.text = attributes[R.styleable.FormFieldLayout_title]
        attributes.recycle()
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)

        if(child != this.titleLabel) {
            child?.setOnFocusChangeListener { view, isFocused ->
               this.updateFocused(isFocused);
            }
        }
    }

    private fun updateFocused(isFocused: Boolean) {
        this.titleLabel.isSelected = isFocused
    }
}