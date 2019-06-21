package com.lordtaylor.cardgame.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.cardgame.R
import com.lordtaylor.cardgame.game_logic.GameViewModel
import com.lordtaylor.cardgame.game_share_preferances.SharedPreferencesProvider
import com.lordtaylor.cardgame.models.SimpleDeck
import kotlinx.android.synthetic.main.game_configuration_fragment.*

class GameConfigurationFragment : Fragment() {


    private lateinit var gameViewModel: GameViewModel
    lateinit var loadedDeck:SimpleDeck

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_configuration_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameViewModel=ViewModelProviders.of(this).get(GameViewModel::class.java)
         loadedDeck =SharedPreferencesProvider.GetSavedDeckID(context!!)

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
            newGameButtonClick()
        }
        if(loadedDeck.deck_id.equals("new")){
            button_load_game.isEnabled = false
        }
        button_load_game.setOnClickListener {
            loadGameButtonClick(loadedDeck)
        }
    }

    private fun loadGameButtonClick(loadedDeck:SimpleDeck) {
        gameViewModel.setDeckFromSP(loadedDeck)
        fragmentManager!!.beginTransaction().replace(R.id.main_container, GameBoardFragment()).commit()
    }

    private fun newGameButtonClick() {
        gameViewModel.setNumberOfDecks(deck_count.progress)
        fragmentManager!!.beginTransaction().replace(R.id.main_container, GameBoardFragment()).commit()
    }

}