 package com.choudhary.newsappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.choudhary.newsappmvvm.R
import com.choudhary.newsappmvvm.ui.Repository.NewsRepository
import com.choudhary.newsappmvvm.ui.Repository.NewsViewModelFactory
import com.choudhary.newsappmvvm.ui.ViewModels.NewsViewModel
import com.choudhary.newsappmvvm.ui.db.ArticleDataBase
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {


    // lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("xx", "onCreate:  inside activity oncreated")


//        val repository = NewsRepository(ArticleDataBase(this))
//        val viewModelFactory = NewsViewModelFactory(repository)
//
//        viewModel   = ViewModelProvider(this,viewModelFactory).get(NewsViewModel::class.java)

//       viewModel.breakingNews.observe(this, Observer {
//
//           var list = it.data?.articles.toString()
//           Log.d("data", "onCreate: "+list)
//       })


        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }




}