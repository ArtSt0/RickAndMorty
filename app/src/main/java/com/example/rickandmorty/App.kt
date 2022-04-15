package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.AppComponent
import com.example.rickandmorty.di.DaggerAppComponent

class App:Application(){
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}