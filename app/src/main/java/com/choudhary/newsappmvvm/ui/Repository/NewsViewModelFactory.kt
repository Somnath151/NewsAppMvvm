package com.choudhary.newsappmvvm.ui.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.choudhary.newsappmvvm.ui.ViewModels.NewsViewModel

class NewsViewModelFactory(private  val repository: NewsRepository)  :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}