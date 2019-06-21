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
        @Path(
            value = "deck_id",
            encoded = true
        ) deck_id: String = "new",
        @Query("deck_count") deck_count: String = "1"
    ): Single<SimpleDeck>

    @GET("deck/{deck_id}/draw/")
    fun getCards(@Path(
        value = "deck_id",
        encoded = true
    ) deck_id: String = "new",
                 @Query("count") deck_count: String = "5"): Single<SimpleCardSet>


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