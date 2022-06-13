package com.terrinc.testbook.data.net

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book

interface BookCloudMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {
        override fun map(id: Int, name: String) = Book(id, name)
    }
}
