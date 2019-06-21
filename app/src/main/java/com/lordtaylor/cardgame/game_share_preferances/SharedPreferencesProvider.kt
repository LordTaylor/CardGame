package com.lordtaylor.cardgame.game_share_preferances

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lordtaylor.cardgame.models.SimpleDeck
import com.lordtaylor.cardgame.utils.Constance

object SharedPreferencesProvider {
    fun getSavedDeck(context: Context): SimpleDeck {
        val pref = context.getSharedPreferences(Constance.PREFERENCES_NAME, Context.MODE_PRIVATE)
        val deck = pref.getString(Constance.DECK_ID, SimpleDeck().toString())
        return Gson().fromJson(deck, SimpleDeck::class.java)
    }

    fun setSaveDeck(context: Context, deck: SimpleDeck) {
        val pref = context.getSharedPreferences(Constance.PREFERENCES_NAME, Context.MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString(Constance.DECK_ID,deck.toString())
        edit.apply()
    }
}