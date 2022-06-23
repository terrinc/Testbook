package com.terrinc.testbook.data

//import com.terrinc.testbook.data.cache.BookDb
//import com.terrinc.testbook.data.cache.BooksCacheDataSource
//import com.terrinc.testbook.data.cache.BooksCacheMapper
//import com.terrinc.testbook.data.net.BookCloud
//import kotlinx.coroutines.runBlocking
//import org.junit.Assert.*
//import org.junit.Test
import java.net.UnknownHostException

class BooksRepositoryTest : BaseBooksRepositoryTest() {

    private val unknownHostException = UnknownHostException()

//    @Test
//    fun test_no_connection_no_cache() = runBlocking {
//
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Fail(unknownHostException)
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_connection_no_cache() = runBlocking {
//
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(listOf(
//            Book(0, "name0"),
//            Book(1, "name1"),
//            Book(2, "name2")
//        ))
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_no_connection_cache() = runBlocking {
//
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(listOf(
//            Book(10, "name10"),
//            Book(11, "name11")
//        ))
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_connection_cache() = runBlocking {
//
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(listOf(
//            Book(10, "name10"),
//            Book(11, "name11")
//        ))
//
//        assertEquals(expected, actual)
//    }
//
//    inner class TestBooksCloudDataSource(
//        private val returnSuccess: Boolean,
//    ) : BooksCloudDataSource {
//        override suspend fun fetchBooks(): List<BookCloud> {
//            if (returnSuccess) {
//                return listOf(
//                    BookCloud(0, "name0"),
//                    BookCloud(1, "name1"),
//                    BookCloud(2, "name2"),
//                )
//            } else {
//                throw unknownHostException
//            }
//        }
//    }
//
//    inner class TestBooksCacheDataSource(private val returnSuccess: Boolean) : BooksCacheDataSource {
//
//        override suspend fun fetchBooks(): List<BookDb> {
//            return if (returnSuccess) {
//                listOf(
//                    BookDb().apply {
//                        id = 10
//                        name = "name10"
//                    },
//                    BookDb().apply {
//                        id = 11
//                        name = "name11"
//                    }
//                )
//            } else {
//                emptyList()
//            }
//        }
//
//        override suspend fun saveBooks(books: List<Book>) {
//            // don't use here
//        }
//    }

}
