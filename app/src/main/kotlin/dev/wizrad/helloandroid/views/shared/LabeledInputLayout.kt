package dev.wizrad.helloandroid.views.shared

import dev.wizrad.helloandroid.R

import android.content.Context
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

internal class LabeledInputLayout : LinearLayout {

    val titleLabel: TextView

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defaultStyle: Int)
        : super(context, attrs, defaultStyle) {

        // pull out necessary attributes values
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.LabeledInputLayout)
        val title = attributes.getText(R.styleable.LabeledInputLayout_hint)
        attributes.recycle()

        // configure layout
        orientation = LinearLayout.VERTICAL

        // insert the title label
        titleLabel = TextView(context)
        titleLabel.text = title
        titleLabel.setTextAppearance(context, android.R.style.TextAppearance_Widget_DropDownHint)
        this.addView(titleLabel)
    }

}