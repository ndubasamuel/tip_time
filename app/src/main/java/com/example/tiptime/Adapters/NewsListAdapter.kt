package com.example.tiptime.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tiptime.Model.Article
import com.example.tiptime.R
import com.squareup.picasso.Picasso

class NewsListAdapter :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.headline_list_item, parent, false)
        return ViewHolder(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)


//    private lateinit var onItemClick: ((String)-> Unit)


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {

            holder.textAuthor.text = article.author
            holder.textContent.text = article.content
            holder.textTitle.text = article.title
            holder.textPublishedAt.text  = article.publishedAt
            holder.textSource.text = article.title
            holder.textDescription.text = article.description


            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.not_available)
//                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageHeadlines)
        }
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textTitle: TextView = itemView.findViewById(R.id.text_title)
        val textDescription: TextView = itemView.findViewById(R.id.text_title1)
        val textAuthor: TextView = itemView.findViewById(R.id.text_author)
        val textContent: TextView = itemView.findViewById(R.id.text_content)
        val textPublishedAt: TextView = itemView.findViewById(R.id.text_publishedAt)
        val textSource: TextView = itemView.findViewById(R.id.text_source)
        val imageHeadlines: ImageView = itemView.findViewById(R.id.img_headline)


    }


//   inner class ViewHolder(
//       private val binding: HeadlineListItemBinding, private val onItemClick: (String) -> Unit?
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: Any) {
//            binding.setVariable(BR.news, data)
//            binding.executePendingBindings()
//
//            binding.newsContainer.setOnClickListener{
//                onItemClick.invoke(binding.news?.url.toString())
//            }
//        }



//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding: HeadlineListItemBinding =
//            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.headline_list_item, parent, false)
//        return ViewHolder(binding, onItemClick)
//
//    }
//    override fun getItemCount(): Int {
//        return newsList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(newsList.get(position))
//    }


}