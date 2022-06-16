package com.terrinc.testbook.data

import com.terrinc.testbook.core.Book
import com.terrinc.testbook.data.cache.BookCacheMapper
import com.terrinc.testbook.data.cache.BookDb
import com.terrinc.testbook.data.net.BookCloudMapper

abstract class BaseBooksRepositoryTest {
    protected inner class TestBookCloudMapper : BookCloudMapper {
        override fun map(id: Int, name: String) = Book(id, name)
    }

    protected inner class TestBookCacheMapper : BookCacheMapper {
        override fun map(bookDb: BookDb) = Book(bookDb.id, bookDb.name)
    }
}
