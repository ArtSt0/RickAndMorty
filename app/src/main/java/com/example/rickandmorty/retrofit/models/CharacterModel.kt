package com.example.rickandmorty.retrofit.models

data class CharacterModel(
    val info: InfoModel,
    val results: ArrayList<PersonModel>
)