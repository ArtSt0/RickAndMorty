package com.example.rickandmorty.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.retrofit.RickAndMortyRepository
import com.example.rickandmorty.retrofit.models.PersonModel
import retrofit2.HttpException
import java.io.IOException

class RickAndMortyPagingSource(
    private val rickAndMortyRepository: RickAndMortyRepository
): PagingSource<Int, PersonModel>(){

    //ключ обновления используется для последующих вызовов
    override fun getRefreshKey(state: PagingState<Int, PersonModel>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonModel> {
        val pageNumber = params.key ?: 1
        return try {
            val response = rickAndMortyRepository.getCharacters(pageNumber)
            val info = response.info
            val results = response.results

            val prevKey:Int? = if(pageNumber == 1) { null }else { pageNumber }

            var nextKey:Int?=null

            info.pages?.let {
                nextKey = if (it>pageNumber){ pageNumber+1 }else{ null }
            }

            LoadResult.Page(
                data = results,
                prevKey = prevKey,
                nextKey = nextKey,
            )

        }catch (exception: IOException){
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}
