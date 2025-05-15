package com.sinisa.bragitask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sinisa.bragitask.domain.models.Movie
import com.sinisa.bragitask.domain.models.Page

class MoviePagingSource(
    private val dataSource: suspend (page: Int, genres: String?) -> Page<Movie>,
    private val genres: String? = null,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val page = dataSource(pageNumber, genres)

            LoadResult.Page(
                data = page.results,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (pageNumber < page.totalPages) pageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}