package com.example.tiptime.network.Models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.R
import com.squareup.picasso.Picasso


class NewsAdapter(private val nList: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_headline)
        val textView: TextView = itemView.findViewById(R.id.text_title)
        val textSource: TextView = itemView.findViewById(R.id.text_source)
        val textAuthor: TextView = itemView.findViewById(R.id.text_author)
        val textTitle: TextView = itemView.findViewById(R.id.text_title1)
        val textPublisher: TextView = itemView.findViewById(R.id.text_publishedAt)
        val textContent: TextView = itemView.findViewById(R.id.text_content)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.headline_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = nList[position]
        holder.textView.text = news.title
        holder.textSource.text = news.source.toString()
        Picasso.get().load(news.urlToImage).into(holder.imageView)
        holder.textAuthor.text = news.author
        holder.textTitle.text = news.description
        holder.textPublisher.text = news.publishedAt
        holder.textContent.text = news.content
    }
    override fun getItemCount(): Int {
        return nList.size
    }

    fun updateData(newData: List<News>?) {
        nList.clear()
        if (newData != null) {
            nList.addAll(newData)
        }
        notifyDataSetChanged()
    }


}


