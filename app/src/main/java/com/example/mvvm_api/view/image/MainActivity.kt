package com.example.mvvm_api.view.image

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvm_api.adapter.MainSearchRecyclerViewAdapter
import com.example.mvvm_api.R
import com.example.mvvm_api.base.BaseKotlinActivity
import com.example.mvvm_api.databinding.ActivityMainBinding
import com.example.mvvm_api.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding,MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter : MainSearchRecyclerViewAdapter by inject()

    override fun initStartView() {
        main_activity_search_recycler_view.run {
            adapter = mainSearchRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(2,1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.imageSearchResponseLiveData.observe(this, Observer {
            it.documents.forEach {document ->
                mainSearchRecyclerViewAdapter
                    .addImageItem(document.image_url, document.doc_url, document.collection, document.display_sitename, document.width, document.height)

            }
            mainSearchRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        main_activity_search_button.setOnClickListener {
            viewModel.getImageSearch(main_activity_search_text_view.text.toString(),1,80)
        }

    }
}
