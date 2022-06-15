package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException


// todo rename to BooksDomain
sealed class BookDomain : Abstract.Object<BooksUi, BookDomainToUiMapper>() {

    class Success(private val books: List<Book>) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
