package com.example.rickandmorty.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.rickandmorty.di.factory.ViewModelFactory
import com.example.rickandmorty.di.qualifier.ViewModelKey
import com.example.rickandmorty.viewModels.DetailViewModel
import com.example.rickandmorty.viewModels.MainViewModel

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}
