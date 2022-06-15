package com.terrinc.testbook.presentation

import com.terrinc.testbook.R
import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    class Success(
        private val books: List<Book>,
        private val communication: BooksCommunication,
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            communication.show(books)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val communication: BooksCommunication,
        private val resourceProvider: ResourceProvider,
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when(errorType) { //todo move to other class
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getString(messageId))
        }
    }
}
