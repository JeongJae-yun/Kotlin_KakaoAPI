package com.example.mvvm_api.di

import com.example.mvvm_api.adapter.MainSearchRecyclerViewAdapter
import com.example.mvvm_api.Model.DataModel
import com.example.mvvm_api.Model.DataModelImpl
import com.example.mvvm_api.Model.service.KakaoSearchService
import com.example.mvvm_api.adapter.VideoSearchRecyclerViewAdapter
import com.example.mvvm_api.viewmodel.MainViewModel
import com.example.mvvm_api.viewmodel.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single<KakaoSearchService> {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoSearchService::class.java)
    }
}

var adapterPart = module {
    factory {
        MainSearchRecyclerViewAdapter()
        VideoSearchRecyclerViewAdapter()
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
        VideoViewModel(get())
    }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)