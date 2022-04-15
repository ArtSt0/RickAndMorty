package com.example.rickandmorty.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.paging.RickAndMortyPagingSource
import com.example.rickandmorty.retrofit.RickAndMortyRepository
import com.example.rickandmorty.retrofit.models.PersonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel(){

    fun getCharactersFlow(): Flow<PagingData<PersonModel>> {
        return Pager(getDefaultPageConfig()){
            RickAndMortyPagingSource(rickAndMortyRepository)
        }.flow.cachedIn(viewModelScope)
    }
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 1)
    }
}