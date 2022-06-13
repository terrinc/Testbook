package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Book
import com.terrinc.testbook.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BookDomain.Success(books)
    override fun map(exception: Exception) = BookDomain.Fail(exception)
}
