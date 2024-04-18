package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
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

        val selectedItem = intent.getParcelableExtra("selectedItem", ItemInfo::class.java)
        if (selectedItem != null) {
            binding.apply {
                ivItemImageInDetailPage.setImageResource(selectedItem.image)
                tvTitleInDetailPage.text = selectedItem.title
                tvSeller.text = selectedItem.seller
                lvItemDetail.text = selectedItem.introduce
                tvLocationInDetailPage.text = selectedItem.location
                tvPriceInDetailPage.text = selectedItem.price
            }
        }
        goBack()
    }

    //        val image = intent.getIntExtra("photo",0)
//        Glide.with(this)
//            .load(image)
//            .into(binding.ivItemImageInDetailPage)
    fun goBack() {
        val intent = Intent(this,MainActivity::class.java)
        binding.ibGoBack.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }


}