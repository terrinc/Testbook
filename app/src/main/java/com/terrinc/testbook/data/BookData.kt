package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.cache.BookDb
import com.terrinc.testbook.domain.BookDataToDomainMapper
import com.terrinc.testbook.domain.BookDomain

data class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String,
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, AbstractObjectWrapper<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper) = mapper.mapToDb(id, name, testament)

    fun compare(temp: TestamentTemp): Boolean = temp.matches(testament)
    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

interface TestamentTemp {
    fun isEmpty(): Boolean
    fun matches(testament: String): Boolean
    fun save(testament: String)

    class Base : TestamentTemp {
        private var temp: String = ""

        override fun isEmpty(): Boolean = temp.isEmpty()

        override fun matches(testament: String) = temp == testament

        override fun save(testament: String) {
            temp = testament
        }
    }
}

//todo make it better
interface AbstractObjectWrapper<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M): T
}
