package com.terrinc.testbook.data

import com.terrinc.testbook.core.Book
import com.terrinc.testbook.data.cache.BookCacheMapper
import com.terrinc.testbook.data.cache.BookDb
import com.terrinc.testbook.data.cache.BooksCacheDataSource
import com.terrinc.testbook.data.cache.BooksCacheMapper
import com.terrinc.testbook.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(listOf(
            Book(0, "name0"),
            Book(1, "name1")
        ))

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            Book(0, "name0 db"),
            Book(1, "name1 db")
        ))

        assertEquals(expectedCache, actualCache)
    }

    private inner class TestBooksCloudDataSource : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0"),
                BookCloud(1, "name1"),
            )
        }
    }

    inner class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override suspend fun fetchBooks() = list

        override suspend fun saveBooks(books: List<Book>) {
            books.map { book ->
                list.add(BookDb().apply {
                    id = book.id
                    name = "${book.name} db"
                })
            }
            list
        }
    }
}
