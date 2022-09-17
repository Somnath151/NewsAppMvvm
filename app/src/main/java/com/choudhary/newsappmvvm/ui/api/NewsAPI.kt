package com.choudhary.newsappmvvm.ui.api

import com.choudhary.newsappmvvm.ui.Utils.Constants.API_KEY
import com.choudhary.newsappmvvm.ui.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {


    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country: String = "in",
        @Query("apikey")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ): Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        querySearch: String = "in",
        @Query("apikey")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ): Response<NewsResponse>
}