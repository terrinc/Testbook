package com.terrinc.testbook.data.cache

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book

interface BooksCacheMapper : Abstract.Mapper {

    fun map(books: List<BookDb>): List<Book>

    class Base(private val mapper: BookCacheMapper) : BooksCacheMapper {
        override fun map(books: List<BookDb>) = books.map { bookDb ->
            bookDb.map(mapper)
        }
    }
}
