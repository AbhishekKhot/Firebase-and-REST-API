package com.example.task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.repository.ArticleRepository

class MainViewModelProviderFactory(val repository: ArticleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsArticleViewModel(repository) as T
    }
}