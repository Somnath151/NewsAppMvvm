package com.choudhary.newsappmvvm.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.choudhary.newsappmvvm.R
import com.choudhary.newsappmvvm.ui.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArcticleViewHolder>() {

    inner  class  ArcticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private  val differCallBack = object  : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArcticleViewHolder {
        return ArcticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ArcticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListner?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListner :((Article) -> Unit)?= null

    fun setOnItemClickListener (listner : (Article) -> Unit){
        onItemClickListner = listner
    }

    override fun getItemCount(): Int {
       return  differ.currentList.size
    }
}