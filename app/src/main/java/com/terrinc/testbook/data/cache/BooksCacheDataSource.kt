package com.terrinc.testbook.data.cache

import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.BookDataToDbMapper

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookDb>

    suspend fun saveBooks(books: List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper,
    ) : BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> {
            return realmProvider.provide().query(BookDb::class).find()
        }

        override suspend fun saveBooks(books: List<BookData>) {
            realmProvider.provide().write {
                books.forEach { book ->
                    val bookDb = book.mapTo(mapper)
                    copyToRealm(bookDb)
                }
            }
        }
    }
}
