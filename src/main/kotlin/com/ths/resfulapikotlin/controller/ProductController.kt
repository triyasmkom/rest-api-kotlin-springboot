package com.ths.resfulapikotlin.controller

import com.ths.resfulapikotlin.model.*
import com.ths.resfulapikotlin.service.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
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

    @DeleteMapping(
        value = ["/api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable ("id_product") id:String):WebResponse<String>{
        productService.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "$id deleted!!"
        )
    }

    @GetMapping(
        value = ["api/products"],
        produces = ["application/json"]
    )
    fun listProduct(
        @RequestParam(value = "size", defaultValue = "10") size:Int,
        @RequestParam(value = "page", defaultValue = "0")page:Int):WebResponse<List<ProductResponse>>{
        val request = ListProductRequest(size = size,page = page)
        val response = productService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}