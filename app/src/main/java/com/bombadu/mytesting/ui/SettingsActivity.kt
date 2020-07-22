package com.bombadu.mytesting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bombadu.mytesting.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content,
                SettingsFragment()
            )
            .commit()


    }
}