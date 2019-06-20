package com.lordtaylor.cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lordtaylor.cardgame.views.GameConfigurationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.main_container,GameConfigurationFragment()).commit()
    }
}
