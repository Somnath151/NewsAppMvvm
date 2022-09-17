package com.choudhary.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.choudhary.newsappmvvm.R
import com.choudhary.newsappmvvm.ui.Adapters.NewsAdapter
import com.choudhary.newsappmvvm.ui.Repository.NewsRepository
import com.choudhary.newsappmvvm.ui.Repository.NewsViewModelFactory
import com.choudhary.newsappmvvm.ui.Utils.Resource
import com.choudhary.newsappmvvm.ui.ViewModels.NewsViewModel
import com.choudhary.newsappmvvm.ui.db.ArticleDataBase


import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var  viewModel : NewsViewModel
    lateinit var  newsAdapter : NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val repository = NewsRepository(ArticleDataBase(requireContext()))
        val viewModelFactory = NewsViewModelFactory(repository)

        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(NewsViewModel::class.java)

        Log.d("xxx", "onViewCreated: inside fragement ")

        //  viewModel  =(activity as MainActivity).viewModel

        setUpRecylerView()

        var job : Job?= null

        etSearch.addTextChangedListener { editable->

            job?.cancel()
            job = MainScope().launch {

                delay(500L)

                editable?.let {

                    if (editable.toString().isNotEmpty()){
                        viewModel.getSearchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response->

            when(response){

                is Resource.Success ->{
                    hideProgress()
                    response.data?.let {  newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error ->{

                    hideProgress()
                    response.messsage?.let {

                        Log.d("somnath", it)
                    }
                }

                is Resource.Loading ->{
                    showProgress()
                }
            }
        })
    }


    private fun hideProgress() {
        paginationProgressBar.visibility = View.INVISIBLE
    }


    private fun showProgress() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecylerView(){

        newsAdapter = NewsAdapter()
        rvSearchNews.apply {

            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}