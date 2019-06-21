package com.lordtaylor.cardgame.views

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.lordtaylor.cardgame.models.SimpleCard
import com.lordtaylor.cardgame.models.SimpleDeck
import com.lordtaylor.cardgame.repository.SimpleGameRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var cardList: List<SimpleCard>
    private val TAG = "GameViewModel"
    private var numberOfDecks: Int = 1
    private var repo: SimpleGameRepository = SimpleGameRepository()
    private var deck: SimpleDeck = SimpleDeck(false, "new", false, 0)

    private val context: Context = application.baseContext
    private lateinit var actions:GameActions


    fun setNumberOfDecks(count: Int) {
        numberOfDecks = count
    }

    fun initGame() {
        getDecks()
    }

    private fun getDecks() {
        repo.getDecks(deck.deck_id, numberOfDecks).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                deck = it
                actions.setRemainingCards(deck.remaining)
                Log.d(TAG, "DECK ID :$it")
            }, {
                Log.e(TAG, "ERROR : ${it.localizedMessage}")
            }
        )
    }

    fun getCard() {
        repo.getCard(deck.deck_id).subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.d(TAG, "DECK ID :${it.cards}")
            cardList=it.cards
            deck.updateDeck(it)
            actions.setCards(cardList)
            actions.setRemainingCards(deck.remaining)
        }, {
            Log.e(TAG, "ERROR : ${it.localizedMessage}")
        })
    }


    fun setGameActionInterface(actions: GameActions){
        this.actions = actions
    }

    fun getReaminingCards(): Int {
        return deck.remaining
    }


}