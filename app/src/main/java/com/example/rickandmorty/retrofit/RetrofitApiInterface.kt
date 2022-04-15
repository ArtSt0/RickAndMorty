package com.example.rickandmorty.retrofit

import com.example.rickandmorty.retrofit.models.CharacterModel
import com.example.rickandmorty.retrofit.models.PersonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface RetrofitApiInterface {
    //@POST("/cases/new_case/")
    //fun newCase(@Body case: Case)

    @GET("api/character/")
    fun getCharacters(@Query("page") page:Int):Call<CharacterModel>
}

class RickAndMortyRepository @Inject constructor(
    private val retrofitApiInterface: RetrofitApiInterface
){
    suspend fun getCharacters(page: Int):CharacterModel{
        return suspendCoroutine {cont->
            val call = retrofitApiInterface.getCharacters(page = page)

            call.enqueue(object :Callback<CharacterModel>{
                override fun onResponse(
                    call: Call<CharacterModel>,
                    response: Response<CharacterModel>
                ) {
                    if (response.isSuccessful){
                        cont.resume(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                    cont.resumeWithException(t)
                }

            })
        }
    }
}

interface OnItemClickPerson{
    fun onItemClickPerson(personModel: PersonModel)
}



