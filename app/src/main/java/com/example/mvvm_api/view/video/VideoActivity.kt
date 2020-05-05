package com.example.mvvm_api.view.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_api.R
import com.example.mvvm_api.adapter.VideoSearchRecyclerViewAdapter
import com.example.mvvm_api.base.BaseKotlinActivity
import com.example.mvvm_api.databinding.ActivityVideoBinding
import com.example.mvvm_api.viewmodel.VideoViewModel
import kotlinx.android.synthetic.main.activity_video.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseKotlinActivity<ActivityVideoBinding,VideoViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_video

    override val viewModel: VideoViewModel by viewModel()

    private val videoSearchRecyclerViewAdapter : VideoSearchRecyclerViewAdapter by inject()

    override fun initStartView() {
        val linearLayoutManager = LinearLayoutManager(this@VideoActivity.applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        video_activity_search_recycler_view.run {
            adapter = videoSearchRecyclerViewAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }

    }

    override fun initDataBinding() {
        viewModel.videoSearchResponseLiveData.observe(this, Observer {
            it.documents.forEach {document ->
                videoSearchRecyclerViewAdapter
                    .addVideoItem(document.title,document.play_time,document.thumbnail,document.url, document.datetime, document.author)
            }
            videoSearchRecyclerViewAdapter.notifyDataSetChanged()
        })

    }

    override fun initAfterBinding() {
        video_activity_search_button.setOnClickListener {
            viewModel.getVideoSearch(video_activity_search_text_view.text.toString(),1,30)
        }

    }


}
