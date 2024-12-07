package com.example.appblog.domain.home

import com.example.appblog.core.Resource
import com.example.appblog.data.model.Post
import com.example.appblog.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpI(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPost(): Resource<List<Post>> = dataSource.getLatestPost()
}