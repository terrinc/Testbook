package com.terrinc.testbook.data

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
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(listOf(
            BookData(0, "name0", "nt"),
            BookData(1, "name1", "nt")
        ))

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            BookData(0, "name0 db", "nt"),
            BookData(1, "name1 db", "nt")
        ))

        assertEquals(expectedCache, actualCache)
    }

    private inner class TestBooksCloudDataSource : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0", "nt"),
                BookCloud(1, "name1", "nt"),
            )
        }
    }

    inner class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override suspend fun fetchBooks() = list

        override suspend fun saveBooks(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapTo(object : BookDataToDbMapper {
                    override fun mapToDb(id: Int, name: String, testament: String): BookDb {
                        return BookDb().apply {
                            this.id = id
                            this.name = "$name db"
                            this.testament = testament
                        }
                    }

                }))
            }
            list
        }
    }
}
