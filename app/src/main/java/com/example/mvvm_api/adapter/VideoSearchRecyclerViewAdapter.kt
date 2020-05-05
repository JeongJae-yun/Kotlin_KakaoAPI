package com.example.mvvm_api.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_api.Model.data.VideoItem
import com.example.mvvm_api.R
import com.example.mvvm_api.util.util
import com.example.mvvm_api.view.video.PlayActivity
import com.example.mvvm_api.view.video.VideoActivity
import kotlinx.android.synthetic.main.item_video_list.view.*

class VideoSearchRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private val util = util()
    }

    private val videoItemList = ArrayList<VideoItem>()

    class VideoHolder(parent: ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_video_list, parent, false)
    ) {
        fun onBind(item:VideoItem){
            itemView.run {
                iv_thumnail.loadThumbnailImage(item.thumbnail)
                tv_title.text = item.title
                tv_date.text = util.changeDateTime(item.datetime)
                tv_playtime.text = util.changeTime(item.play_time)
                tv_author.text = item.author
                setOnClickListener {
                    val intent = Intent(it.context, PlayActivity::class.java)
                    intent.putExtra("url",item.url)
                    it.context.startActivity(intent)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoHolder(
            parent
        )

    override fun getItemCount(): Int = videoItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? VideoHolder)?.onBind(videoItemList[position])
    }

    fun addVideoItem(
        title : String,
        play_time : Int,
        thumbnail : String,
        url : String,
        datetime : String,
        author : String
    ){
        videoItemList.add(VideoItem(title, play_time, thumbnail, url, datetime, author))
    }

}