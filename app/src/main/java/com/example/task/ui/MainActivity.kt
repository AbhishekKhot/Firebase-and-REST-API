package com.example.task.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.example.task.repository.ArticleRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
     lateinit var viewModel: NewsArticleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val repository = ArticleRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsArticleViewModel::class.java)


    }
}