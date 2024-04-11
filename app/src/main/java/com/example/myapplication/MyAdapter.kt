package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerviewItemBinding

class MyAdapter(val itemList: List<ItemInfo>) :
    RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    inner class MyAdapterViewHolder(val itemBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(items: ItemInfo) {
            itemBinding.tvComments.text = items.comment
            itemBinding.ivItemImage.setImageResource(items.image)
            itemBinding.tvLikes.text = items.like.toString()
            itemBinding.tvTitle.text = items.title
            itemBinding.tvLocation.text = items.location
            itemBinding.tvPrice.text = items.price
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        return MyAdapterViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val item = itemList[position]
        holder.bindItem(item)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailPageActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context,intent,null)

        }
    }
}

