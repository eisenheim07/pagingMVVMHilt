package com.example.pagingmvvmhilt.models.model_users

data class GetUsers(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)