package com.terrinc.testbook.core

import android.app.Application
import com.google.gson.Gson
import com.terrinc.testbook.data.BookDataToDbMapper
import com.terrinc.testbook.data.BooksCloudDataSource
import com.terrinc.testbook.data.BooksCloudMapper
import com.terrinc.testbook.data.BooksRepository
import com.terrinc.testbook.data.ToBookMapper
import com.terrinc.testbook.data.cache.BooksCacheDataSource
import com.terrinc.testbook.data.cache.BooksCacheMapper
import com.terrinc.testbook.data.cache.RealmProvider
import com.terrinc.testbook.data.net.BooksService
import com.terrinc.testbook.domain.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.terrinc.testbook.domain.BaseBooksDataToDomainMapper
import com.terrinc.testbook.domain.BooksInteractor
import com.terrinc.testbook.presentation.BaseBookDomainToUiMapper
import com.terrinc.testbook.presentation.BaseBooksDomainToUiMapper
import com.terrinc.testbook.presentation.BooksCommunication
import com.terrinc.testbook.presentation.MainViewModel
import com.terrinc.testbook.presentation.ResourceProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class TestBookApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val service = retrofit.create(BooksService::class.java)

        val gson = Gson()
        val cloudDataSource = BooksCloudDataSource.Base(service, gson)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base(), BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper,
        )
        val bookMapper = BaseBookDataToDomainMapper()
        val interactor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper(bookMapper))
        val communication = BooksCommunication.Base()
        mainViewModel = MainViewModel(
            booksInteractor = interactor,
            mapper = BaseBooksDomainToUiMapper(ResourceProvider.Base(this), BaseBookDomainToUiMapper()),
            communication = communication,
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}
