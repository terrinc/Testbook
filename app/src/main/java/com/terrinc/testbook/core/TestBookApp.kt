package com.terrinc.testbook.core

import android.app.Application
import com.terrinc.testbook.domain.BooksInteractor
import com.terrinc.testbook.presentation.BaseBooksDomainToUiMapper
import com.terrinc.testbook.presentation.BooksCommunication
import com.terrinc.testbook.presentation.MainViewModel
import com.terrinc.testbook.presentation.ResourceProvider

class TestBookApp: Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val booksInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(
            booksInteractor = booksInteractor,
            mapper = BaseBooksDomainToUiMapper(BooksCommunication.Base(), ResourceProvider.Base(this)),
            communication = BooksCommunication.Base(),
        )
    }
}
