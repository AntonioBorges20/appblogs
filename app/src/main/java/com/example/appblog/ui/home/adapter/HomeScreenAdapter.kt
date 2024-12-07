package com.example.appblog.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appblog.core.BaseViewHolder
import com.example.appblog.data.model.Post
import com.example.appblog.databinding.PostItemViewBinding

class HomeScreenAdapter(private val postList: List<Post>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding=PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return HomeScreenViewHolder(itemBinding,parent.context)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HomeScreenViewHolder -> holder.bin(postList[position])
        }
    }

    private inner class HomeScreenViewHolder(
        val binding: PostItemViewBinding,
        val context: android.content.Context
    ):BaseViewHolder<Post>(binding.root){
        override fun bin(item: Post) {
            Glide.with(context).load(item.post_Image).centerCrop().into(binding.postImage)
            Glide.with(context).load(item.profile_picture).centerCrop().into(binding.profilePicture)
            binding.profileName.text=item.profile_name
            binding.postTimestamp.text = "Hace 2 horas"
        }

    }
}