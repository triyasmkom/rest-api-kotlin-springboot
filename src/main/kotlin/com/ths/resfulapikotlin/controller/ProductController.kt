package com.ths.resfulapikotlin.controller

import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ProductResponse
import com.ths.resfulapikotlin.model.WebResponse
import com.ths.resfulapikotlin.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController (val productService: ProductService){

    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody req: CreateProductRequest): WebResponse<ProductResponse>{
        val productResponse = productService.create(req)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }
}