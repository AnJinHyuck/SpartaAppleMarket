package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailPageBinding

private lateinit var binding: ActivityDetailPageBinding

class DetailPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val image = intent.getIntExtra("photo",0)
        Glide.with(this)
            .load(image)
            .into(binding.ivItemImageInDetailPage)

        binding.tvTitleInDetailPage.text = intent.getStringExtra("title")
        binding.tvSeller.text = intent.getStringExtra("seller")
        binding.lvItemDetail.text = intent.getStringExtra("detailinfo")
        binding.tvLocationInDetailPage.text = intent.getStringExtra("location")

    }

}