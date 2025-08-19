package com.advertise.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var  rvNames:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvNames)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override  fun onResume(){
        super.onResume()
        rvNames = findViewById(R.id.rvNames)
        rvNames.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val names = listOf("caro","Joy","Hanny","Aloo","Esther","Nancy","Bright")
        val namesAdapter=NamesRvAdapter(names)
        rvNames.adapter=namesAdapter
    }

}















