package com.terrinc.testbook.data.net

import com.google.gson.annotations.SerializedName
import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book

//{"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}}
data class BookCloud(
    @SerializedName("id")
    private val id: Int,

    @SerializedName("name")
    private val name: String,
) : Abstract.Object<Book, BookCloudMapper>() {

    override fun map(mapper: BookCloudMapper) = mapper.map(id, name)

}
