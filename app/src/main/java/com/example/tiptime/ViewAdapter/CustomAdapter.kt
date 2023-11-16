package com.example.tiptime.ViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.databinding.HeadlineListItemBinding
import com.example.tiptime.network.Models.NewsHeadlines
import com.squareup.picasso.Picasso

class CustomAdapter(private val headlines: List<NewsHeadlines>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){


    inner class ViewHolder(val binding: HeadlineListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val inflater = LayoutInflater.from(context)
        //val view = inflater.inflate(R.layout.headline_list_item, parent, false)
        val binding = HeadlineListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textTitle.text = headlines[position].title
//        holder.textSource.text = headlines[position].source?.name
//        holder.cardView.rootView

holder.apply {
    binding.textTitle.text = headlines[position].title
    binding.textSource.text = headlines[position].source.name
}

        Picasso.get().load(headlines[position].urlToImage).into(holder.binding.imgHeadline)
    }
        override fun getItemCount(): Int {
            return headlines.size;
        }

    fun updateData(newsList: List<NewsHeadlines>) {
        newsList
    }

}





