package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MyAdapter(Itemlist.list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        makeList()
        goDetailPage()
        floatingUp()
        onBackPressedDispatcher.addCallback(this, callBack)
        binding.ivBell.setOnClickListener {
            notification(this)
        }

    }


    private val callBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("종료?")
                setIcon(R.drawable.left)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("예") { _, _ ->
                    finish()
                }
                setNegativeButton("아니요") { _, _ ->
                }
                show()
            }
        }
    }


    fun goDetailPage() {
        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(item: ItemInfo) {
                val intent = Intent(this@MainActivity, DetailPageActivity::class.java)
                intent.putExtra("selectedItem", item)
                startActivity(intent)
            }
        }
    }

    fun makeList() {
        binding.mainItem.adapter = adapter

    }

    fun floatingUp() {
        val floatbtn = binding.floatingbtn
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 200 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 200 }

        var isTop = true

        binding.mainItem.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.mainItem.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    floatbtn.startAnimation(fadeOut)
                    floatbtn.visibility = View.GONE
                    isTop = true
                } else if (isTop) {
                    floatbtn.visibility = View.VISIBLE
                    floatbtn.startAnimation(fadeIn)
                    isTop = false
                }
            }
        })

        floatbtn.setOnClickListener{
            binding.mainItem.smoothScrollToPosition(0)
        }
    }


}