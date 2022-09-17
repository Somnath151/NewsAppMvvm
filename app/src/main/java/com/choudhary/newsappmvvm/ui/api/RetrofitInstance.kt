package com.choudhary.newsappmvvm.ui.api

import com.choudhary.newsappmvvm.ui.Utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        private  val retrofit by lazy {

          Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()

        }

        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}