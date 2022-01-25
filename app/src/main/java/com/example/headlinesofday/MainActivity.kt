package com.example.headlinesofday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var  mAdepter:NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        layout manger
        recycleView.layoutManager=LinearLayoutManager(this)
        fetchData()
        mAdepter= NewsListAdapter(this)
        recycleView.adapter=mAdepter

    }
    private fun fetchData(){
        val url="https://newsapi.org/v2/everything?q=apple&from=2022-01-24&to=2022-01-24&sortBy=popularity&apiKey=094b9d78b3364f87b80edecb3c81df5e"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray=it.getJSONArray("articles")
                val newsArray=ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject=newsJsonArray.getJSONObject(i)
                    val news=News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                mAdepter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {

    }


}


