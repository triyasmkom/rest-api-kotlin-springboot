package com.ths.resfulapikotlin.repository

import com.ths.resfulapikotlin.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String>{
}