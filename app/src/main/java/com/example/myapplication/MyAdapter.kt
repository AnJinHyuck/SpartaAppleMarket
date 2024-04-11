package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerviewItemBinding

class MyAdapter(val itemList :List<ItemInfo>) : RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {
    inner class MyAdapterViewHolder(val itemBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
            fun bindItem(items:ItemInfo){
                itemBinding.tvComments.text = items.comment
                itemBinding.ivItemImage.setImageResource(items.image)
                itemBinding.tvLikes.text = items.like.toString()
                itemBinding.tvTitle.text = items.title
                itemBinding.tvLocation.text=items.location
                itemBinding.tvPrice.text=items.price
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        return MyAdapterViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val item = itemList[position]
        holder.bindItem(item)
    }
}

