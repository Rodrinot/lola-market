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
    @GET("styles/?key=a1d4112b073924d9b42aa486ab6b5c2a")
    fun getStyles() : Observable<Styles>

    @GET("beers/")
    fun getBeers(@Query("styleId") styleId : String, @Query("key") key : String) : Observable<Beers>
}