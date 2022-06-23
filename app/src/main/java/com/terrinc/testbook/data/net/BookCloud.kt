package com.terrinc.testbook.data.net

import com.google.gson.annotations.SerializedName
import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.ToBookMapper

//{"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}}
data class BookCloud(
    @SerializedName("id")
    private val id: Int,

    @SerializedName("name")
    private val name: String,
) : Abstract.Object<BookData, ToBookMapper> {

    override fun map(mapper: ToBookMapper) = mapper.map(id, name)

}
