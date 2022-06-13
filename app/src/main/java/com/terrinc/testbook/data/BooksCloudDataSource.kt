package com.terrinc.testbook.data

import com.terrinc.testbook.data.net.BookCloud
import com.terrinc.testbook.data.net.BooksService

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BooksService) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }
}
