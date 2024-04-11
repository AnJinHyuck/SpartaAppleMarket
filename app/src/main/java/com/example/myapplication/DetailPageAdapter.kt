package com.example.myapplication

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.myapplication.databinding.ActivityDetailPageBinding

private lateinit var binding: ActivityDetailPageBinding

class DetailPageAdapter(val context: Context, val itemList: ArrayList<ItemInfo>) : BaseAdapter() {
    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Any {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_detail_page, null)
        val items = itemList[position]
        binding.ivItemImageInDetailPage.setImageResource(items.image)
        binding.tvSeller.text = items.seller
        binding.lvItemDetail.movementMethod = ScrollingMovementMethod.getInstance()
        binding.lvItemDetail.text = items.introduce
        return view
    }
}