package com.ths.resfulapikotlin.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Id

data class ProductResponse (
    val id: String,
    val name: String,
    val price: Long,
    val quantity: Int,
    val createdAt: Date,
    val updatedAt: Date?
    )