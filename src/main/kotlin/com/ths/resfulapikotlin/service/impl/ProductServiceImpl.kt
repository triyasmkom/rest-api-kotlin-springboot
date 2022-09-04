package com.ths.resfulapikotlin.service.impl

import com.ths.resfulapikotlin.ValidationUtils
import com.ths.resfulapikotlin.entity.Product
import com.ths.resfulapikotlin.error.NotFoundException
import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ProductResponse
import com.ths.resfulapikotlin.model.UpdateProductRequest
import com.ths.resfulapikotlin.repository.ProductRepository
import com.ths.resfulapikotlin.service.ProductService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtils: ValidationUtils
): ProductService{
    override fun create(req: CreateProductRequest) : ProductResponse{
        validationUtils.validate(req)
        val product = Product(
            id = req.id!!,
            name = req.name!!,
            price = req.price!!,
            quantity = req.quantity!!,
            createdAt = Date(),
            updatedAt = null

        )

        productRepository.save(product);

        return response(product)
    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id)
        if(product==null){
            throw NotFoundException()
        } else {
            return response(product)
        }
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = productRepository.findByIdOrNull(id)
        if(product==null){
            throw NotFoundException()
        }

        validationUtils.validate(updateProductRequest)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)
        return response(product)

    }

    private fun response(product: Product):ProductResponse{
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

}