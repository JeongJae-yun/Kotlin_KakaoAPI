package com.example.mvvm_api.Model

import com.example.mvvm_api.Model.enum.KakaoSearchSortEnum
import com.example.mvvm_api.Model.response.ImageSearchResponse
import com.example.mvvm_api.Model.response.VideoSearchResponse
import com.example.mvvm_api.Model.service.KakaoSearchService
import io.reactivex.Single

class DataModelImpl(private val service:KakaoSearchService):DataModel{

    /*
    * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
    * 공식문서 보고 작성
    * */


    private val KAKAO_APP_KEY = "f26559018dc3ff123711324db198990a"

    override fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    } //single을 반환해준다.

    override fun getVideoData(query: String, sort: KakaoSearchSortEnum, page: Int, size: Int): Single<VideoSearchResponse> {
        return service.searchVideo(auth = "KakaoAK ${KAKAO_APP_KEY}", query = query, sort = sort.sort, page = page, size = size)
    }
}