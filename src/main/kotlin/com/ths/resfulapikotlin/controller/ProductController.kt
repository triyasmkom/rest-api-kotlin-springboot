package com.ths.resfulapikotlin.controller

import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ProductResponse
import com.ths.resfulapikotlin.model.UpdateProductRequest
import com.ths.resfulapikotlin.model.WebResponse
import com.ths.resfulapikotlin.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @GetMapping(
        value = ["/api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id_product") id:String):WebResponse<ProductResponse>{
        val productResponse = productService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["/api/products/{id_product}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("id_product") id: String,
                      @RequestBody updateProductRequest: UpdateProductRequest):WebResponse<ProductResponse>{
        val productResponse = productService.update(id, updateProductRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }
}