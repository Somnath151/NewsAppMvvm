package com.choudhary.newsappmvvm.ui.ViewModels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choudhary.newsappmvvm.ui.Repository.NewsRepository
import com.choudhary.newsappmvvm.ui.Utils.Resource
import com.choudhary.newsappmvvm.ui.models.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

   var   breakingNews : MutableLiveData<Resource<NewsResponse>>
   var   searchNews : MutableLiveData<Resource<NewsResponse>>

    init {
        breakingNews = MutableLiveData( )
        searchNews = MutableLiveData()
        getBreakingnews("in")


    }



    var breakingnewsPage = 1

    fun getBreakingnews(countryCode: String) = viewModelScope.launch {

        breakingNews.postValue(Resource.Loading())
        val repsonse = newsRepository.getBreakingNews(countryCode,breakingnewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(repsonse))

    }


    fun getSearchNews(searchquery : String) {
        searchNews.postValue(Resource.Loading())

       viewModelScope.launch {
           val reponse = newsRepository.searchNews(searchquery,1)
           searchNews.postValue(handleSearchNewsResponse(reponse))
       }

    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{

        if (response.isSuccessful){

            response.body()?.let {

                return  Resource.Success(it)
            }
        }

        return  Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{

        if (response.isSuccessful){

            response.body()?.let {

                return  Resource.Success(it)
            }
        }

        return  Resource.Error(response.message())
    }








}