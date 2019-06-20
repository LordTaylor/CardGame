package com.lordtaylor.cardgame.models

import com.google.gson.annotations.SerializedName

class SimpleDeck(@SerializedName("success") val success:Boolean,@SerializedName("deck_id") val deck_id: String, var shuffled:Boolean, var remaining:Int) :BaseDeck {
    fun updateDeck(decSet: SimpleCardSet) {

     remaining = decSet.remaining
    }
}