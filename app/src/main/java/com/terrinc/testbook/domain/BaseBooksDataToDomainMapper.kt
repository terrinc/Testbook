package com.terrinc.testbook.domain

import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.BooksDataToDomainMapper
import com.terrinc.testbook.data.TestamentTemp
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>): BooksDomain.Success {
        val temp = TestamentTemp.Base()
        val booksDomain = mutableListOf<BookDomain>()
        books.forEach { bookData ->
            if (!bookData.compare(temp)) {
                if (temp.isEmpty()) {
                    booksDomain.add(BookDomain.Testament(TestamentType.OLD))
                } else {
                    booksDomain.add(BookDomain.Testament(TestamentType.NEW))
                }
                bookData.saveTestament(temp)
            }
            booksDomain.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(booksDomain)
    }

    override fun map(exception: Exception): BooksDomain.Fail {
        val errorType = when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
        return BooksDomain.Fail(errorType)
    }
}
