package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

private lateinit var binding : ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private val adapter = MyAdapter(Itemlist.list)
    val itemList = Itemlist.list

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
    }

//    fun goDetailPage(){
//        adapter.itemClick = object : MyAdapter.ItemClick{
//            override fun onClick(view: View, position: Int) {
//                val intent = Intent(adapter.)
//                Toast.makeText(this@MainActivity,"$title 선택", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    fun makeList(){
        binding.mainItem.adapter = adapter
    }

}