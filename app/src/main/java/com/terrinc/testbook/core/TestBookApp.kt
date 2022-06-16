package com.terrinc.testbook.core

import android.app.Application
import com.terrinc.testbook.data.BooksCloudDataSource
import com.terrinc.testbook.data.BooksCloudMapper
import com.terrinc.testbook.data.BooksRepository
import com.terrinc.testbook.data.cache.BookCacheMapper
import com.terrinc.testbook.data.cache.BooksCacheDataSource
import com.terrinc.testbook.data.cache.BooksCacheMapper
import com.terrinc.testbook.data.cache.RealmProvider
import com.terrinc.testbook.data.net.BookCloudMapper
import com.terrinc.testbook.data.net.BooksService
import retrofit2.Retrofit
import com.terrinc.testbook.domain.BaseBooksDataToDomainMapper
import com.terrinc.testbook.domain.BooksInteractor
import com.terrinc.testbook.presentation.BaseBooksDomainToUiMapper
import com.terrinc.testbook.presentation.BooksCommunication
import com.terrinc.testbook.presentation.MainViewModel
import com.terrinc.testbook.presentation.ResourceProvider

class TestBookApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper,
        )
        val interactor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
        val communication = BooksCommunication.Base()
        mainViewModel = MainViewModel(
            booksInteractor = interactor,
            mapper = BaseBooksDomainToUiMapper(communication, ResourceProvider.Base(this)),
            communication = communication,
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }
}
