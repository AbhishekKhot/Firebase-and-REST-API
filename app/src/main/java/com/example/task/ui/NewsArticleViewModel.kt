package com.example.task.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.NewsResponse
import com.example.task.repository.ArticleRepository
import com.example.task.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsArticleViewModel(val repository: ArticleRepository) : ViewModel() {
    val getNewsArticles: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getNewsArticles()
    }

    fun getNewsArticles() = viewModelScope.launch {
        getNewsArticles.postValue(Resource.Loading())
        val response = repository.getNewsArticles()
        getNewsArticles.postValue(ImplementNewsReponse(response))
    }

    private fun ImplementNewsReponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}