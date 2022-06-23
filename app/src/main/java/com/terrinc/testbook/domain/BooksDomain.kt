package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    class Success(
        private val books: List<BookData>,
        private val bookMapper: BookDataToDomainMapper,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            val booksDomain = books.map { bookData ->
                bookData.map(bookMapper)
            }
            return mapper.map(booksDomain)
        }
    }

    class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
