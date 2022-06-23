package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.cache.BookDb
import com.terrinc.testbook.domain.BookDataToDomainMapper
import com.terrinc.testbook.domain.BookDomain

class BookData(
    private val id: Int,
    private val name: String,
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, AbstractObjectWrapper<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper) = mapper.mapToDb(id, name)
}
 //todo make it better
interface AbstractObjectWrapper<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M): T
}
