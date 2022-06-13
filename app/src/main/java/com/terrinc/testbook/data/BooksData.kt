package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.domain.BookDomain

sealed class BooksData : Abstract.Object<BookDomain, BooksDataToDomainMapper>() {

    class Success(private val books: List<Book>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)
    }

    class Fail(private val exception: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(exception)
    }

}
