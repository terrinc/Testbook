package com.terrinc.testbook.domain

import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>) = BooksDomain.Success(books, bookMapper)
    override fun map(exception: Exception) = BooksDomain.Fail(exception)
}
