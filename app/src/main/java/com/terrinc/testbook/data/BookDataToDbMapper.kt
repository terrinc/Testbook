package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.cache.BookDb

interface BookDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, testament: String): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament: String) = BookDb().apply {
            this.id = id
            this.name = name
            this.testament = testament
        }
    }
}
