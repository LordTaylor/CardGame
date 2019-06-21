package com.lordtaylor.cardgame.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.lordtaylor.cardgame.R
import com.lordtaylor.cardgame.game_logic.GameViewModel
import com.lordtaylor.cardgame.models.SimpleCard
import kotlinx.android.synthetic.main.game_board_fragment.*

class GameBoardFragment : Fragment(), GameActions {
    private val TAG = "GameBoardFragment"

    private lateinit var gameViewModel: GameViewModel
    private lateinit var cardAdapter: RecyclerCardAdapter
    private val numberOfCardsInRow: Int = 5


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
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
        text_card_count.text = "${getText(R.string.card_count)}$remaining"
    }

    override fun startGame() {

    }
}