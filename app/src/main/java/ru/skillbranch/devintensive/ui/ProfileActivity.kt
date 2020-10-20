package ru.skillbranch.devintensive.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.skillbranch.devintensive.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }
}