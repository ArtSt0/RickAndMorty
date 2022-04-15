package com.example.rickandmorty.di

import android.app.Application
import com.example.rickandmorty.DetailPersonActivity
import com.example.rickandmorty.MainActivity
import dagger.BindsInstance
import dagger.Component
import com.example.rickandmorty.di.modules.AppModule
import com.example.rickandmorty.di.modules.RetrofitModule
import com.example.rickandmorty.di.modules.ViewModelFactoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelFactoryModule::class,
    RetrofitModule::class
])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(detailPersonActivity: DetailPersonActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}