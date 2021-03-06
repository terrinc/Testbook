package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.domain.BooksDomain

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper> {

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)
    }

    data class Fail(private val exception: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(exception)
    }

}
