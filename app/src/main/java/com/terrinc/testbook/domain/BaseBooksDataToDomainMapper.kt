package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Book
import com.terrinc.testbook.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BooksDomain.Success(books)
    override fun map(exception: Exception) = BooksDomain.Fail(exception)
}
