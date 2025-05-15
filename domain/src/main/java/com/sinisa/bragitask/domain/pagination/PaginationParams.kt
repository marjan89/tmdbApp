package com.sinisa.bragitask.domain.pagination

data class PaginationParams(
    val page: Int,
    val pageSize: Int = DEFAULT_PAGE_SIZE
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        val INITIAL = PaginationParams(1)
    }
}