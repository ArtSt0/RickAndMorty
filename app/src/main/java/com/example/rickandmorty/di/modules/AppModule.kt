package com.example.rickandmorty.di.modules

import android.content.Context
import com.example.rickandmorty.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: App): Context
}
