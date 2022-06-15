package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BookDomainToUiMapper>() {

    class Success(private val books: List<Book>) : BooksDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
