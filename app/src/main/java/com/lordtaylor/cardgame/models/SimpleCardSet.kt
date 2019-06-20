package com.lordtaylor.cardgame.models

import com.google.gson.annotations.SerializedName

class SimpleCardSet (@SerializedName("success") val success:Boolean, @SerializedName("deck_id") val deck_id: String, var cards:List<SimpleCard>, var remaining:Int) :BaseDeck {
}