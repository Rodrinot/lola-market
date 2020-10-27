package com.example.android.lolamarket.models

data class Styles(
        val message: String,
        val data: ArrayList<Style>,
        val status: String
) {
    data class Style(
            val id : String,
            val name : String
    )
}