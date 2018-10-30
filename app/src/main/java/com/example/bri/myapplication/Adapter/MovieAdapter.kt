package com.example.bri.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bri.myapplication.BuildConfig
import com.example.bri.myapplication.Model.MovieModel
import com.example.bri.myapplication.R
import kotlinx.android.synthetic.main.post_layout.view.*
import java.util.ArrayList

/**
 * Created by BRI on 23/10/2018.
 */
class MovieAdapter(internal var context: Context, movies: List<MovieModel>?) : BaseAdapter() {
    internal var movies: List<MovieModel> ? = null

    init {
        this.movies = movies
    }

    override fun getCount(): Int {
        return movies!!.size
    }

    override fun getItem(i: Int): Any {
        return movies!![i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
            var layoutInflater = LayoutInflater.from(context)
            var viewMoview = layoutInflater.inflate(R.layout.post_layout, null)

        viewMoview.title.text = movies!![i].title
        viewMoview.content.text = movies!![i].release_date
        viewMoview.author.text = movies!![i].original_title

        Glide.with(context)
                .load(BuildConfig.URL_BASE_IMAGE + "" + movies!![i].poster_path)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(viewMoview.image_view)

        return viewMoview
    }
}
