package com.example.appblog.data.remote.home

import com.example.appblog.core.Resource
import com.example.appblog.data.model.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {
    suspend fun getLatestPost() :Resource<List<Post>>{
        val postList = mutableListOf<Post>()
        val querySnapshot=FirebaseFirestore.getInstance().collection("post").get().await()
        for(post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let{ fbPost ->
                fbPost.apply { post_timestamp = post.getTimestamp("created_at", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)?.toDate() }
                postList.add(fbPost)
            }
        }
        return Resource.Success(postList)
    }
}