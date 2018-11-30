package com.amadoutirera.maakomome.home.Base_recyclerview_viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class Base_ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

