package com.example.mvvm_api.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_api.Model.data.ImageItem
import com.example.mvvm_api.R
import kotlinx.android.synthetic.main.item_main_image.view.*

class MainSearchRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ImageHolder(parent: ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_main_image, parent, false)
    ) {
        fun onBind(item:ImageItem){
            itemView.run {
                item_main_image_view.run {
                    loadImage(item.imageUrl,item.width,item.height)
                    setOnClickListener {
                        //item click시 doc url에 따라 Action View 넘겨줌
                        ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(item.documentUrl)), null)
                    }
                }
            }
        }
    }

    private val imageItemList = ArrayList<ImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageHolder(
            parent
        )

    override fun getItemCount() = imageItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageHolder)?.onBind(imageItemList[position])
    }

    fun addImageItem(imageUrl: String, documentUrl: String, collection : String, display_sitename : String, width : Int, height : Int){
        imageItemList.add(ImageItem(imageUrl, documentUrl, collection, display_sitename,width,height))
    }

}