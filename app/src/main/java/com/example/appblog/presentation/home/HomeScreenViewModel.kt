package com.example.appblog.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.appblog.core.Resource
import com.example.appblog.data.model.Post
import com.example.appblog.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeScreenViewModel(private val repo: HomeScreenRepo): ViewModel() {

    fun fetchLatestPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getLatestPost())
        }catch (e: java.lang.Exception){
            emit(Resource.Failure(e))
        }
    }


    // with Flow coroutine builder
    val latestPosts: StateFlow<Resource<List<Post>>> = flow {
        kotlin.runCatching {
            repo.getLatestPost()
        }.onFailure { throwable ->
            emit(Resource.Failure(Exception(throwable)))
        }.onSuccess { postList ->
            emit(postList)
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000), // Or Lazily because it's a one-shot
        initialValue = Resource.Loading()
    )

    //without Flow coroutine builder
    private val posts = MutableStateFlow<Resource<List<Post>>>(Resource.Loading())

    fun fetchPosts() {
        viewModelScope.launch {
            kotlin.runCatching {
                repo.getLatestPost()
            }.onFailure { throwable ->
                posts.value = Resource.Failure(Exception(throwable))
            }.onSuccess { postList ->
                posts.value = postList
            }
        }
    }

    fun getPosts() = posts

}


class HomeScreenViewModelFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }

}
