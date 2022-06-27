package com.terrinc.testbook.presentation

import com.terrinc.testbook.R
import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.domain.BookDomain
import com.terrinc.testbook.domain.BookDomainToUiMapper
import com.terrinc.testbook.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    data class Success(
        private val books: List<BookDomain>,
        private val bookMapper: BookDomainToUiMapper,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val booksUi = books.map { bookDomain ->
                bookDomain.map(bookMapper)
            }
            mapper.map(booksUi)
        }
    }

    data class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }
}
