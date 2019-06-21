package com.lordtaylor.cardgame.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.lordtaylor.cardgame.R
import com.lordtaylor.cardgame.R.drawable.round_background
import com.lordtaylor.cardgame.game_logic.GameViewModel
import com.lordtaylor.cardgame.game_share_preferances.SharedPreferencesProvider
import com.lordtaylor.cardgame.models.SimpleCard
import kotlinx.android.synthetic.main.card_item.*
import kotlinx.android.synthetic.main.game_board_fragment.*

class GameBoardFragment(var cardsInStack: Int = 0) : Fragment(), GameActions {
    private val TAG = "GameBoardFragment"

    private lateinit var gameViewModel: GameViewModel
    private lateinit var cardAdapter: RecyclerCardAdapter
    private val numberOfCardsInRow: Int = 5


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        gameViewModel = ViewModelProviders.of(activity!!).get(GameViewModel::class.java)
        gameViewModel.setGameActionInterface(this)
        gameViewModel.initGame()
        initViews()
    }

    private fun initViews() {
        card_container.layoutManager = GridLayoutManager(context, numberOfCardsInRow)
        cardAdapter = RecyclerCardAdapter(context!!, listOf())
        card_container.adapter = cardAdapter
        button_draw.setOnClickListener {
            drawCard()
        }
        button_shuffle.setOnClickListener {
            shuffleDeck()
        }
        text_win_lose.visibility = View.INVISIBLE
    }

    private fun shuffleDeck() {
        gameViewModel.initGame()
        cardAdapter.setCardsList(listOf())
    }

    private fun drawCard() {
        gameViewModel.getCard()
    }

    override fun setCards(cardList: List<SimpleCard>) {
        cardAdapter.setCardsList(cardList)

    }

    override fun setRemainingCards(remaining: Int) {
        this.cardsInStack = remaining
        if (text_win_lose != null) {
            if (this.cardsInStack > 0) {
                text_win_lose.visibility = View.INVISIBLE
                imageView.setImageResource(R.drawable.magic_card)
            } else {
                noCardsInDeck()
                imageView.setImageResource(R.drawable.blank_card)
            }
        }
    }

    override fun playerWins() {
        if (text_win_lose != null) {
            text_win_lose.visibility = View.VISIBLE
            text_win_lose.text = getText(R.string.you_win)

        }

    }

    override fun noCardsInDeck() {
        if (text_win_lose != null) {
            text_win_lose.visibility = View.VISIBLE
            text_win_lose.text = getText(R.string.no_cards_left)
        }
    }

    override fun onPause() {
        super.onPause()
        SharedPreferencesProvider.setSaveDeck(context!!, gameViewModel.getDeck())
    }
}