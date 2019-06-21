package com.lordtaylor.cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.cardgame.game_logic.GameViewModel
import com.lordtaylor.cardgame.views.GameBoardFragment
import com.lordtaylor.cardgame.views.GameConfigurationFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, GameConfigurationFragment())
            .addToBackStack("game").commit()
    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
    }
}
