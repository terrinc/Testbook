package com.terrinc.testbook.presentation

import com.terrinc.testbook.core.Book
import com.terrinc.testbook.domain.BooksDomainToUiMapper
import com.terrinc.testbook.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider,
) : BooksDomainToUiMapper {

    override fun map(books: List<Book>) = BooksUi.Success(books, communication)

    override fun map(errorType: ErrorType) = BooksUi.Fail(errorType, communication, resourceProvider)
}
