package com.example.mvvm_api.Model

import com.example.mvvm_api.Model.enum.KakaoSearchSortEnum
import com.example.mvvm_api.Model.response.ImageSearchResponse
import com.example.mvvm_api.Model.response.VideoSearchResponse
import io.reactivex.Single

interface DataModel {
    fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse>

    fun getVideoData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<VideoSearchResponse>
}