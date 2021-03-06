package com.vunh.recyclerkotlinmvvm.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vunh.login_kotlin_mvvm.utils.AppUtils
import com.vunh.recyclerkotlinmvvm.model.Movie
import com.vunh.recyclerkotlinmvvm.model.Response
import com.vunh.recyclerkotlinmvvm.model.Status
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RecyclerService {

//    @FormUrlEncoded
    @GET("pda/movie/list")
    fun callListAsync(
//        @Field("username") username: String,
//        @Field("password") password: String,
    ): Deferred<List<Movie>>

    @GET("pda/movie/get")
    fun callGetAsync(
        @Query("movieId") movieId: String,
    ): Deferred<Response>

    @FormUrlEncoded
    @POST("pda/movie/add")
    fun callInsertAsync(
        @Field("movieId") movieId: String,
        @Field("category") category: String,
        @Field("imageUrl") imageUrl: String,
        @Field("name") name: String,
        @Field("desc") desc: String,
    ): Deferred<Status>

    @FormUrlEncoded
    @POST("pda/movie/update")
    fun callUpdateAsync(
        @Field("movieId") movieId: String,
        @Field("category") category: String,
        @Field("imageUrl") imageUrl: String,
        @Field("name") name: String,
        @Field("desc") desc: String,
    ): Deferred<Status>

    @FormUrlEncoded
    @POST("pda/movie/delete")
    fun callDeleteAsync(
        @Field("movieId") movieId: String,
    ): Deferred<Status>

    companion object {
        var recyclerService: RecyclerService? = null

        fun getInstance() : RecyclerService {
            if (recyclerService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(AppUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

                recyclerService = retrofit.create(RecyclerService::class.java)
            }
            return recyclerService!!
        }
    }
}