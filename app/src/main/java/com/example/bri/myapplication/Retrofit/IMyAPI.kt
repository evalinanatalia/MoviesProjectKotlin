package com.example.bri.myapplication.Retrofit

import com.example.bri.myapplication.Model.PageModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by BRI on 23/10/2018.
 */
interface IMyAPI{
    @GET("popular")
    fun getMovie_popular(
            @Query("api_key") api_key : String
    ):Call<PageModel>

    @GET("top_rated")
    fun getMovie_toprated(
            @Query("api_key") api_key : String
    ):Call<PageModel>

    @GET("upcoming")
    fun getMovie_upcoming(
            @Query("api_key") api_key : String
    ):Call<PageModel>

}