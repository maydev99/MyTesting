package com.bombadu.mytesting.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bombadu.mytesting.database.MyData
import com.bombadu.mytesting.database.MyViewModel
import com.bombadu.mytesting.util.Util

class AdderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content,
                AdderFragment()
            )
            .commit()
    }

}