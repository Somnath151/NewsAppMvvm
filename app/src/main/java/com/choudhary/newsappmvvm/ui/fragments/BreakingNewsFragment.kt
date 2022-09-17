package com.choudhary.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.choudhary.newsappmvvm.R
import com.choudhary.newsappmvvm.ui.Adapters.NewsAdapter
import com.choudhary.newsappmvvm.ui.MainActivity
import com.choudhary.newsappmvvm.ui.Repository.NewsRepository
import com.choudhary.newsappmvvm.ui.Repository.NewsViewModelFactory
import com.choudhary.newsappmvvm.ui.Utils.Resource
import com.choudhary.newsappmvvm.ui.ViewModels.NewsViewModel
import com.choudhary.newsappmvvm.ui.db.ArticleDataBase
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_breaking_news, container, false)
//    }
//




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

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response->

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
        rvBreakingNews.apply {

            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}