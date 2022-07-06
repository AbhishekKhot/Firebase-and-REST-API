package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.RvItemBinding
import com.example.task.model.Article

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArticleViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        with(holder) {
            binding.tvTitle.text = article.title.toString()
            binding.tvPublishedAt.text = article.publishedAt.toString()
            binding.tvSource.text = article.source.toString()
            binding.tvDescription.text = article.description.toString()

            Glide.with(holder.itemView)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.ivArticleImage)

        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}