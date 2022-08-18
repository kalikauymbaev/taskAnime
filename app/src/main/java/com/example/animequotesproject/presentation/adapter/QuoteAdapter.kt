package com.example.animequotesproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.AnimeQuote
import com.example.animequotesproject.R
import com.example.animequotesproject.databinding.FullInfoLayoutBinding

class QuoteAdapter(private val list: List<AnimeQuote>): RecyclerView.Adapter<QuoteAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = FullInfoLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.full_info_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        with(holder){
            binding.tvAnime.text = "Anime: ${item.anime}"
            binding.tvCharacter.text = "Character: ${item.character}"
            binding.tvQuote.text = "QUOTE: ${item.quote}"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}