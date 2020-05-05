package com.example.mvvm_api.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_api.Model.DataModel
import com.example.mvvm_api.Model.enum.KakaoSearchSortEnum
import com.example.mvvm_api.Model.response.VideoSearchResponse
import com.example.mvvm_api.base.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VideoViewModel (private val model: DataModel) : BaseKotlinViewModel() {

    private val TAG = "VideoViewModel"

    private val _videoSearchResponseLiveData = MutableLiveData<VideoSearchResponse>()
    val videoSearchResponseLiveData: LiveData<VideoSearchResponse>
        get() = _videoSearchResponseLiveData


    fun getVideoSearch(query: String, page:Int, size:Int) {
        addDisposable(model.getVideoData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (documents.size > 0) {
                        Log.d(TAG, "documents : $documents")
                        _videoSearchResponseLiveData.postValue(this) //postValue는 UI스레드가 아니라도 UI변경 가능. setValue는 불가능.
                    }
                    Log.d(TAG, "meta : $meta")
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            }))
    }

}