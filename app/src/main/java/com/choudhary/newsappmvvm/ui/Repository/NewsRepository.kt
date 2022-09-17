package com.choudhary.newsappmvvm.ui.Repository

import com.choudhary.newsappmvvm.ui.api.RetrofitInstance
import com.choudhary.newsappmvvm.ui.db.ArticleDao
import com.choudhary.newsappmvvm.ui.db.ArticleDataBase

class NewsRepository(
    val db : ArticleDataBase
) {

    suspend fun getBreakingNews(countryCode : String,pageNum : Int) =

        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber = pageNum)


    suspend fun  searchNews(searchQuery: String, pageNum: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber = pageNum)




}