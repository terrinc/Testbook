package com.terrinc.testbook.data.cache

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper {

    fun map(books: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookMapper>>) = books.map { bookDb ->
            bookDb.map(mapper)
        }
    }
}
