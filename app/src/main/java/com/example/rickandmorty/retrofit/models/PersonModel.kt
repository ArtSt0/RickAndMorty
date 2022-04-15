package com.example.rickandmorty.retrofit.models

import java.io.Serializable

data class PersonModel(
    val id:Int,
    val name: String?="",
    val status: String?="",
    val species: String?="",
    val type: String?="",
    val gender: String?="",
    val origin: Any?,
    val location: LocationModel?,
    val image: String?,
    val episode: ArrayList<String>?,
    val url:String?="",
    val created: String?=""
): Serializable