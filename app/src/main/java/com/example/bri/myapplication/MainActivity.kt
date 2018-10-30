package com.example.bri.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.example.bri.myapplication.Adapter.MovieAdapter
import com.example.bri.myapplication.Model.MovieModel
import com.example.bri.myapplication.Model.PageModel
import com.example.bri.myapplication.Retrofit.IMyAPI
import com.example.bri.myapplication.Retrofit.RetrofitClient
import com.example.bri.myapplication.Util.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var iMyAPI : IMyAPI ? = null
    private val API_KEY = "3b561a9deada4994d60aa22b621966e9"
    var pageModels: List<MovieModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = RetrofitClient.instance
        iMyAPI = retrofit.create(IMyAPI::class.java)
        fetchPosts("4")
    }

    private fun fetchPosts(check: String) {
        var pageModelCall = iMyAPI!!.getMovie_toprated(API_KEY)
        when (check) {
            "1" -> pageModelCall = iMyAPI!!.getMovie_popular(API_KEY)
            "2" -> pageModelCall = iMyAPI!!.getMovie_toprated(API_KEY)
            "3" -> pageModelCall = iMyAPI!!.getMovie_upcoming(API_KEY)
            "4" -> pageModelCall = iMyAPI!!.getMovie_upcoming(API_KEY)
        }
        //            if(Utils.isInternetAvailable()) {
        pageModelCall.enqueue(object : Callback<PageModel> {
            override fun onResponse(call: Call<PageModel>, response: Response<PageModel>) {
                pageModels = response.body()!!.results
                displayData(pageModels)
                progressBar_cyclic.setVisibility(View.GONE)
            }

            override fun onFailure(call: Call<PageModel>, t: Throwable) {
                //                    Log.e("Error", t.toString());
                Utils.getToastMessage(getBaseContext(), t.toString())!!.show()
                progressBar_cyclic.setVisibility(View.GONE)
            }
        })
        //            }else {
        //                progressBar.setVisibility(View.GONE);
        //                Utils.getToastMessage(this, "Network Disconnect").show();
        //            }

    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean{
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun displayData(movieModels: List<MovieModel>?) {
        val adapter = MovieAdapter(this, movieModels)
        rows.setAdapter(adapter)
    }

}

