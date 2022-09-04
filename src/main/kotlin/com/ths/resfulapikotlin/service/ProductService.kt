package com.ths.resfulapikotlin.service

import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ProductResponse
import com.ths.resfulapikotlin.model.UpdateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest):ProductResponse

    fun get(id: String):ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest):ProductResponse
}