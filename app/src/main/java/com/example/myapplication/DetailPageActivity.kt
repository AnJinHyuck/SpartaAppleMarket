package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailPageBinding

//private lateinit var binding: ActivityDetailPageBinding
//Do not place Android context classes in static field... 오류메시지
//원인은 인스턴스가 static이 아닌데도 클래스 레벨에서 선언되었기 때문. -> 메모리 누수가 발생할 수도 있음(아닐수도)
class DetailPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPageBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectedItem = intent.getParcelableExtra("selectedItem",ItemInfo::class.java)

        if (selectedItem != null) {
            binding.ivItemImageInDetailPage.setImageResource(selectedItem.image)
            binding.tvTitleInDetailPage.text = selectedItem.title
            binding.tvSeller.text = selectedItem.seller
            binding.lvItemDetail.text = selectedItem.introduce
            binding.tvLocationInDetailPage.text = selectedItem.location
            binding.tvPriceInDetailPage.text = selectedItem.price
        }
//        val image = intent.getIntExtra("photo",0)
//        Glide.with(this)
//            .load(image)
//            .into(binding.ivItemImageInDetailPage)
    }

}