package com.terrinc.testbook.core

import android.app.Application
import com.terrinc.testbook.data.BooksRepository
import com.terrinc.testbook.domain.BaseBooksDataToDomainMapper
import com.terrinc.testbook.domain.BooksInteractor

class TestBookApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val booksRepository: BooksRepository = TODO("merge")
        val interactor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
    }
}
