package com.example.omerseyfettinyavuzyigitsahibindencopy.util

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showToastLong(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.showToastShort(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
fun EditText.textLength(): Int {
    return text.trim().length
}
fun EditText.textString(): String {
    return text.toString()
}