package com.ths.resfulapikotlin.repository

import com.ths.resfulapikotlin.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
}