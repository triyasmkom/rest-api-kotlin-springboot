package com.ths.resfulapikotlin.service

import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ProductResponse

interface ProductService {
    fun create(createProductRequest: CreateProductRequest):ProductResponse
}