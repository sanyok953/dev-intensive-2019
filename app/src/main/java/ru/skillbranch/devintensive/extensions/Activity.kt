package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.opengl.ETC1.getHeight



fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View = this.currentFocus
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView: ViewGroup = window.decorView.rootView as ViewGroup
    val r = Rect()

    rootView.getWindowVisibleDisplayFrame(r)

    val screenHeight = rootView.height
    val keypadHeight = screenHeight - r.bottom

    return keypadHeight > screenHeight * 0.15
}

fun Activity.isKeyboardClosed (): Boolean {
    val rootView: ViewGroup = window.decorView.rootView as ViewGroup
    val r = Rect()

    rootView.getWindowVisibleDisplayFrame(r)

    val screenHeight = rootView.height
    val keypadHeight = screenHeight - r.bottom

    return keypadHeight < screenHeight * 0.15
}