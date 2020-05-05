package com.example.mvvm_api.Model.response

data class VideoSearchResponse(
    val documents:ArrayList<Document>,
    val meta: Meta
){
    data class Document(
        val title : String,
        val play_time : Int,
        val thumbnail : String,
        val url : String,
        val datetime : String,
        val author : String
    )

    data class Meta(
        val is_end: Boolean,
        val pageable_count: Int,
        val total_count: Int
    )
}

