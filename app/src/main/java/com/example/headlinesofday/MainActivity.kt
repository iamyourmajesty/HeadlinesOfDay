package com.example.headlinesofday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        layout manger
        recycleView.layoutManager=LinearLayoutManager(this)
        val items=fetchData()
        val adapter= NewsListAdapter(items,this)
        recycleView.adapter=adapter

    }
    private fun fetchData():ArrayList<String>{
        val list=ArrayList<String>()
        for(i in 0 until 100){
            list.add("item $i")
        }
        return list
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this,"clicked item is $item",Toast.LENGTH_SHORT).show()
    }


}