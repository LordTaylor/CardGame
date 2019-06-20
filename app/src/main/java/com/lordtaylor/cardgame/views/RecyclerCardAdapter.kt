package com.lordtaylor.cardgame.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lordtaylor.cardgame.R
import com.lordtaylor.cardgame.models.SimpleCard
import com.squareup.picasso.Picasso

class RecyclerCardAdapter(var context:Context,var cards:List<SimpleCard>) :RecyclerView.Adapter<CardViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item,parent,false))
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(cards[position])
    }

    fun setCardsList(cards:List<SimpleCard>){
        this.cards = cards
        notifyDataSetChanged()
    }

}


class CardViewHolder(var view: View):RecyclerView.ViewHolder(view){
    lateinit var image:ImageView
    fun onBind(simpleCard: SimpleCard){
        image = view.findViewById(R.id.card_image)
        Picasso.get().load(simpleCard.image).into(image)
    }

}