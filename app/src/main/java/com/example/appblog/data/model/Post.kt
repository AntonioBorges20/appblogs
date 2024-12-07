package com.example.appblog.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Post (val profile_picture: String = "",
                 val profile_name: String = "",
                 @ServerTimestamp
                 var post_timestamp: Date?=null,
                 val post_Image: String = "",
                 val post_description: String = "",
                 val uid: String = "")




