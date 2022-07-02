package com.terrinc.testbook.presentation

import com.terrinc.testbook.R
import com.terrinc.testbook.domain.BookDomain
import com.terrinc.testbook.domain.BookDomainToUiMapper
import com.terrinc.testbook.domain.BooksDomainToUiMapper
import com.terrinc.testbook.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper,
) : BooksDomainToUiMapper {

    override fun map(books: List<BookDomain>) = BooksUi.Success(books.map { bookDomain ->
        bookDomain.map(bookMapper)
    })

    override fun map(errorType: ErrorType): BooksUi.Fail {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Fail(message)
    }

}
