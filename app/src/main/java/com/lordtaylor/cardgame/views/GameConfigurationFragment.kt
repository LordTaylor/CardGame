package com.lordtaylor.cardgame.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.cardgame.R
import kotlinx.android.synthetic.main.game_configuration_fragment.*

class GameConfigurationFragment : Fragment() {


    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_configuration_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel=ViewModelProviders.of(this).get(GameViewModel::class.java)

        text_deck_count.text = "${getText(R.string.number_of_decks)}1"
        deck_count.progress = 0
        deck_count.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current deck count
                text_deck_count.text = "${getText(R.string.number_of_decks)}${i + 1}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

        })
        button_start_game.setOnClickListener {
            gameViewModel.setNumberOfDecks(deck_count.progress)
            fragmentManager!!.beginTransaction().replace(R.id.main_container,GameBoardFragment()).commit()
        }
    }
}