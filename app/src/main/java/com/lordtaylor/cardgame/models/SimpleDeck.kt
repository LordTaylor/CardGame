package com.lordtaylor.cardgame.models

import com.google.gson.annotations.SerializedName

class SimpleDeck(
    @SerializedName("success") val success: Boolean = false, @SerializedName("deck_id") val deck_id: String = "new", var shuffled: Boolean = false,
    var remaining: Int = 0
) : BaseDeck {
    fun updateDeck(decSet: SimpleCardSet) {

        remaining = decSet.remaining
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleDeck

        if (success != other.success) return false
        if (deck_id != other.deck_id) return false
        if (shuffled != other.shuffled) return false
        if (remaining != other.remaining) return false

        return true
    }

    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + deck_id.hashCode()
        result = 31 * result + shuffled.hashCode()
        result = 31 * result + remaining
        return result
    }

    override fun toString(): String {
        return "{\"success\":\"$success\", \"deck_id\":'$deck_id', \"shuffled\":'$shuffled', \"remaining\":'$remaining'}"
    }
}