package com.example.appblog.domain.home

import com.example.appblog.core.Resource
import com.example.appblog.data.model.Post

interface HomeScreenRepo {
    suspend fun  getLatestPost(): Resource<List<Post>>
}