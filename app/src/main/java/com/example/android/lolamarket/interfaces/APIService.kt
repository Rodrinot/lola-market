package com.example.android.lolamarket.interfaces

import com.example.android.lolamarket.models.Beers
import com.example.android.lolamarket.models.Styles
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Describe the request type and the relative URL.
 */
interface APIService {
    @GET("styles/")
    fun getStyles(@Query("key") key : String) : Observable<Styles>

    @GET("beers/")
    fun getBeers(@Query("styleId") styleId : String, @Query("key") key : String) : Observable<Beers>
}