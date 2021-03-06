package com.terrinc.testbook.data.net

import okhttp3.ResponseBody
import retrofit2.http.GET

interface BooksService {

    @GET("books")
    suspend fun fetchBooks(): ResponseBody
}
