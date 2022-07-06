package com.example.task.repository

import com.example.task.network.RetrofitInstance

class ArticleRepository {
    suspend fun getNewsArticles() =
        RetrofitInstance.api.getNewsArticles()
}