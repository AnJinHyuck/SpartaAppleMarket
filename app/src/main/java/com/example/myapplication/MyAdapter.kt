package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Itemlist.list
import com.example.myapplication.databinding.RecyclerviewItemBinding
import kotlin.coroutines.coroutineContext

class MyAdapter(val itemList: List<ItemInfo>) :
    RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {
    interface ItemClick {
        fun onClick(item: ItemInfo)
    }

    var itemClick: ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        return MyAdapterViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    //inner class는 정당한 이유가 아니면 안 쓰는게 낫다 메모리 누수가 벌어짐
    class MyAdapterViewHolder(val itemBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(items: ItemInfo) {
            itemBinding.apply {
                tvComments.text = items.comment
                ivItemImage.setImageResource(items.image)
                tvLikes.text = items.like.toString()
                tvTitle.text = items.title
                tvLocation.text = items.location
                tvPrice.text = items.price
            }
        }

    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val item = itemList[position]
        holder.bindItem(item)
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(item)

//            val context = holder.itemView.context
//            val intent = Intent(context, DetailPageActivity::class.java)

//            intent.putExtra("selectedItem", item)
//            intent.putExtra("title", item.title)
//            intent.putExtra("detailinfo", item.introduce)
//            intent.putExtra("seller", item.seller)
//            intent.putExtra("location", item.location)
            //context.startActivity(intent)
        }
    }
}

