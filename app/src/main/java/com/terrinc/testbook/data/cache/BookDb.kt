package com.terrinc.testbook.data.cache

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class BookDb : RealmObject, Abstract.Object<Book, BookCacheMapper>() {

    @PrimaryKey
    var id: Int = -1
    var name: String = ""

    override fun map(mapper: BookCacheMapper) = Book(id, name)
}
