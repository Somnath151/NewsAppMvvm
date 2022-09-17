package com.choudhary.newsappmvvm.ui.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.choudhary.newsappmvvm.ui.models.Article
import com.choudhary.newsappmvvm.ui.models.NewsResponse

@Dao
interface ArticleDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getArticles() : LiveData<List<Article>>


    @Delete()
    suspend fun deleteArticle(article: Article)
}