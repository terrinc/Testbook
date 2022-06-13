package com.terrinc.testbook.data

// todo don't push it
interface BooksRepository {

    suspend fun fetchBooks(): BooksData
}
