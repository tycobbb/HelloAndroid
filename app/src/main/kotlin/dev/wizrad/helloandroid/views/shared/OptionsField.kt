package dev.wizrad.helloandroid.views.shared

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.bindView
import dev.wizrad.helloandroid.R
import dev.wizrad.helloandroid.extensions.inflate
import dev.wizrad.helloandroid.extensions.unwrap
import dev.wizrad.helloandroid.extensions.get
import rx.Observable
import rx.subjects.BehaviorSubject

internal class OptionsField : FrameLayout {

    //
    // Children
    //

    val textField: EditText by bindView(R.id.options_field_internal)

    //
    // Properties
    //

    var unfocusOnSelection: Boolean
    var options: List<String>? = null
    var text: String
        get() { return textField.text.toString() }
        set(value) { textField.setText(value, TextView.BufferType.EDITABLE) }

    private var _selectedOption: BehaviorSubject<Int>

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defaultStyle: Int)
        : super(context, attrs, defaultStyle) {

        inflate(R.layout.view_options_field, context);

        _selectedOption = BehaviorSubject.create()

        // apply optional styleable attributes
        val attributes = attrs.unwrap(R.styleable.OptionsField, context)
        val shouldUnfocus: Boolean? = attributes[R.styleable.OptionsField_unfocusOnSelection]
        textField.hint     = attributes[R.styleable.OptionsField_hint]
        unfocusOnSelection = shouldUnfocus ?: false
        attributes.recycle()

        // show/hide the dialog as the field comes into focus
        textField.setOnFocusChangeListener { view, isFocused ->
            // propagate events through to our listener
            onFocusChangeListener?.onFocusChange(view, isFocused)

            if(isFocused) {
                this.showDialog()
            }
        }
    }

    //
    // Selection
    //

    fun selectedOption() : Observable<Int> {
        return _selectedOption.asObservable()
    }

    fun selections() : Observable<Unit> {
        return this.selectedOption()
            .filter { isFocused }
            .map { Unit }
    }

    //
    // Dialog
    //

    private fun showDialog() {
        assert(options != null, { "must have options when showing dialog" })

        AlertDialog.Builder(context)
            .setTitle(R.string.summoner_region_hint)
            .setItems(options!!.toTypedArray(), this.onSelected())
            .setNegativeButton(R.string.action_cancel, null)
            .show()
    }

    private fun onSelected() : (DialogInterface, Int) -> Unit {
        return { dialog, index ->
            this.shiftFocus()
            _selectedOption.onNext(index)
        }
    }

    private fun shiftFocus() {
        if(unfocusOnSelection) {
            this.clearFocus()
        }
    }

}