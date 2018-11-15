package com.example.kotlin.tool

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlin.R


class ClearableEditTextWithIcon : AppCompatEditText, TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {

    private val right = resources.getDrawable(R.mipmap.clean_icon)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        addTextChangedListener(this)
        onFocusChangeListener = this
        setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.x > width - paddingRight - right.intrinsicWidth && null != compoundDrawables[2]) {
                setText("")
            }
        }
        return false
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.length > 0 && hasFocus())
            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], right, compoundDrawables[3])
        else
            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], null, compoundDrawables[3])
    }

    override fun afterTextChanged(s: Editable) {}

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus && text!!.length > 0) {
            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], right, compoundDrawables[3])
        } else {
            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], null, compoundDrawables[3])
        }
    }

}
