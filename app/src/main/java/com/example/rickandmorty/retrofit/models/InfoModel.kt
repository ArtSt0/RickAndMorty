package com.example.rickandmorty.retrofit.models

data class InfoModel(
    val count: Int?=0,
    val pages: Int?=0,
    val next:String?="",
    val prev:String?=""
)