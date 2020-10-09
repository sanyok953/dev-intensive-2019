package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View? = this.currentFocus
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val DPT = 128
    val r = Rect()
    val rootView: View = findViewById(android.R.id.content)
    rootView.getWindowVisibleDisplayFrame(r)
    val metrics = rootView.resources.displayMetrics
    val heightDiff = rootView.bottom - r.bottom
    return heightDiff > DPT * metrics.density
}

fun Activity.isKeyboardClosed (): Boolean {
    return !isKeyboardOpen()
}