package com.terrinc.testbook.data.cache

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book

interface BookCacheMapper : Abstract.Mapper {

    fun map(bookDb: BookDb): Book

    class Base : BookCacheMapper {
        override fun map(bookDb: BookDb) = Book(bookDb.id, bookDb.name)
    }
}
