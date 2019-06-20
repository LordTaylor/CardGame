package com.lordtaylor.cardgame.views

import com.lordtaylor.cardgame.models.SimpleCard

interface GameActions {
    fun setCards(cardList: List<SimpleCard>)
    fun updateCards()
    fun startGame()
}