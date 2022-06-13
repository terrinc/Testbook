package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.data.net.BookCloud
import com.terrinc.testbook.data.net.BookCloudMapper

interface BooksCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<BookCloud>): List<Book>

    class Base(private val bookMapper: BookCloudMapper) : BooksCloudMapper {
        override fun map(cloudList: List<BookCloud>): List<Book> {
            return cloudList.map { bookCloud ->
                bookCloud.map(bookMapper)
            }
        }
    }
}
