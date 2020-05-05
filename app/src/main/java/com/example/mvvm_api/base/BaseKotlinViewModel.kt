package com.example.mvvm_api.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseKotlinViewModel : ViewModel(){
    /*
    * RxJava의 Observing을 위한 부분.
    * addDisposable을 이용하여 추가만 하면 된다.
    * */

    private val compositeDisposable = CompositeDisposable() //개념 찾기

    //Model에서 들어오는 single같은 RxJava객체들의 Observing을 위한 부분.
    //기본적으로 RxJava의 Observable들은 compositeDisposable에 추가하고 
    // 뷰모델이 없어질 때 추가했던 것을 지워준다.

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable) //Observable들을 추가
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}