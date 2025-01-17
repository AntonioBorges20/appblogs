package com.example.appblog.core

import android.view.View
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder <T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bin(item: T)
}