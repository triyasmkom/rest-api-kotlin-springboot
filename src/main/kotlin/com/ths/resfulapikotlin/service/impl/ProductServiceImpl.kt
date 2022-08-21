package com.ths.resfulapikotlin.service.impl

import com.ths.resfulapikotlin.ValidationUtils
import com.ths.resfulapikotlin.entity.Product
import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ProductResponse
import com.ths.resfulapikotlin.repository.ProductRepository
import com.ths.resfulapikotlin.service.ProductService
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