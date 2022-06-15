package com.terrinc.testbook.domain

import com.terrinc.testbook.data.BooksDataToDomainMapper
import com.terrinc.testbook.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper,
    ) : BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchBooks().map(mapper)
    }
}
