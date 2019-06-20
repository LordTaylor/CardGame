package com.lordtaylor.cardgame.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private var numberOfDecks: Int = 1

    fun setNumberOfDecks(count: Int) {
        numberOfDecks = count
    }
}