package com.example.mvvm_api.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_api.Model.DataModel
import com.example.mvvm_api.Model.enum.KakaoSearchSortEnum
import com.example.mvvm_api.Model.response.ImageSearchResponse
import com.example.mvvm_api.base.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val model: DataModel) : BaseKotlinViewModel(){

    /*
    * _언더바가 붙은 변수만 내부에서 livedata변경이 가능하다..
    * 언더바가 없는 변수는 외부에서 참조함으로써 postValue는 안되고 observe로 데이터 변경 확인만 가능하다.
    *
    * */

    private val TAG = "MainViewModel"

    private val _imageSearchResponseLiveData = MutableLiveData<ImageSearchResponse>()
    val imageSearchResponseLiveData: LiveData<ImageSearchResponse>
        get() = _imageSearchResponseLiveData

    fun getImageSearch(query: String, page:Int, size:Int) {
        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (documents.size > 0) {
                        Log.d(TAG, "documents : $documents")
                        _imageSearchResponseLiveData.postValue(this) //postValue는 UI스레드가 아니라도 UI변경 가능. setValue는 불가능.
                    }
                    Log.d(TAG, "meta : $meta")
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            }))
    }

}