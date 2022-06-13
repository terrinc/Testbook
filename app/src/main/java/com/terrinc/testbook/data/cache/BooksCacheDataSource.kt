package com.terrinc.testbook.data.cache

import com.terrinc.testbook.core.Book

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookDb>

    suspend fun saveBooks(books: List<Book>)

    class Base(private val realmProvider: RealmProvider) : BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> {
            return realmProvider.provide().query(BookDb::class).find()
        }

        override suspend fun saveBooks(books: List<Book>) {
            realmProvider.provide().write {
                books.forEach { book ->
                    val bookDb = BookDb()
                    bookDb.id = book.id
                    bookDb.name = book.name
                    copyToRealm(bookDb)
                }
            }
        }
    }
}
