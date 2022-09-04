package com.ths.resfulapikotlin.service.impl

import com.ths.resfulapikotlin.ValidationUtils
import com.ths.resfulapikotlin.entity.Product
import com.ths.resfulapikotlin.error.NotFoundException
import com.ths.resfulapikotlin.model.CreateProductRequest
import com.ths.resfulapikotlin.model.ListProductRequest
import com.ths.resfulapikotlin.model.ProductResponse
import com.ths.resfulapikotlin.model.UpdateProductRequest
import com.ths.resfulapikotlin.repository.ProductRepository
import com.ths.resfulapikotlin.service.ProductService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.List

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
        val product = findProductByIdOrThrowNotFound(id)
        return response(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
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

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val product: kotlin.collections.List<Product> = page.get().collect(Collectors.toList())

        return product.map { response(it) }
    }

    private fun findProductByIdOrThrowNotFound(id:String):Product{
        val product = productRepository.findByIdOrNull(id)
        if(product==null){
            throw NotFoundException()
        } else{
            return product
        }
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