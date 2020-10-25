package com.example.android.lolamarket.interfaces

import com.example.android.lolamarket.models.BaseModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Describe the request type and the relative URL.
 */
interface GetData {
    @GET("styles/?key=a1d4112b073924d9b42aa486ab6b5c2a")
    fun getData() : Observable<BaseModel>
}