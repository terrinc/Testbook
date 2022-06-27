package com.terrinc.testbook.data.cache

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.ToBookMapper
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class BookDb : RealmObject, Abstract.Object<BookData, ToBookMapper> {

    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun map(mapper: ToBookMapper) = BookData(id, name, testament)
}
