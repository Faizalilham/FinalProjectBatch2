package com.example.finalprojectbatch2.API

import com.example.finalprojectbatch2.model.Barang
import com.example.finalprojectbatch2.model.Model
import retrofit2.Call
import retrofit2.http.*

interface APIEndPoint {

    //RetrofitLogin
   @FormUrlEncoded
   @POST("auth/sign-up")
   fun SignUp(@Field("name")name : String,
              @Field("username")username : String,
              @Field("email")email : String,
              @Field("password")password : String
   ):Call<SingleRespon<Model>>

   @FormUrlEncoded
   @POST("auth/sign-in")
   fun SignIn(@Field("username")username: String,
   @Field("password")password: String):Call<SingleRespon<Model>>

   //RetrofitCRUD
   @GET("barang")
   fun GetBarang():Call<ListRespon<Barang>>

   @GET("barang/{id}")
   fun GetById(@Path("id")id : Int): Call<ListRespon<Barang>>

    @DELETE("barang/{id}")
    fun DeleteById(@Path("id")id : Int):Call<Void>

    @FormUrlEncoded
    @POST("barang/")
    fun PostBarang(@Field("nama")nama : String,
                 @Field("kode")kode :Int) : Call<SingleRespon<Barang>>

    @FormUrlEncoded
    @PUT("barang/{id}")
    fun UpdateBarang(@Path("id")id:Int,
                   @Field("nama")nama : String,
                   @Field("kode")kode :Int) : Call<SingleRespon<Barang>>


}
data class ListRespon<T>(
        var msg : String,
        var status : Int,
        var data : MutableList<Barang>
)
data class SingleRespon<T>(
        var msg : String,
        var status : Int,
        var data :T
)