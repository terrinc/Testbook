package com.terrinc.testbook.presentation

import com.terrinc.testbook.domain.BookDomain
import com.terrinc.testbook.domain.BookDomainToUiMapper
import com.terrinc.testbook.domain.BooksDomainToUiMapper
import com.terrinc.testbook.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {

    override fun map(books: List<BookDomain>) = BooksUi.Success(books, bookMapper)

    override fun map(errorType: ErrorType) = BooksUi.Fail(errorType, resourceProvider)

}
