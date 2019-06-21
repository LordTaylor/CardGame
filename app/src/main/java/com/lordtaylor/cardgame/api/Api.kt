package com.lordtaylor.cardgame.api

import com.google.gson.GsonBuilder
import com.lordtaylor.cardgame.models.SimpleCard
import com.lordtaylor.cardgame.models.SimpleCardSet
import com.lordtaylor.cardgame.models.SimpleDeck
import com.lordtaylor.cardgame.utils.Constance
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {


    @GET("deck/{deck_id}/shuffle/")
    fun getDecks(
        @Path("deck_id") deck_id: String ,
        @Query("deck_count") count: Int
    ): Single<SimpleDeck>

    @GET("deck/{deck_id}/draw/")
    fun getCards(@Path("deck_id") deck_id: String ,
                 @Query("count") count: String = Constance.NUMBER_OF_CARDS_TO_DRAW): Single<SimpleCardSet>


    companion object {
        fun create(): Api {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constance.BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}