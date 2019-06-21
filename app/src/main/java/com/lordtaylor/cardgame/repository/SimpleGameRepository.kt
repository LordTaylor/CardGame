package com.lordtaylor.cardgame.repository

import com.lordtaylor.cardgame.api.Api
import com.lordtaylor.cardgame.models.SimpleCard
import com.lordtaylor.cardgame.models.SimpleCardSet
import com.lordtaylor.cardgame.models.SimpleDeck
import io.reactivex.Single

class SimpleGameRepository :BaseRepository {
    fun getDecks(deck_id:String,deck_count:Int) : Single<SimpleDeck>{
        return api.getDecks(deck_id,deck_count)
    }

    fun getCard(deck_id: String):Single<SimpleCardSet> {
        return api.getCards(deck_id)
    }

    private var api:Api
    init {
        api = Api.create()
    }
}