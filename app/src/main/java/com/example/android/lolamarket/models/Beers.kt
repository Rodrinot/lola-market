package com.example.android.lolamarket.models

data class Beers(
        val totalResults: Int,
        val data: ArrayList<Beer>,
        val status: String
) {
    data class Beer(
            val id: String,
            val name: String,
            val labels: Labels
    ) {
        data class Labels(
                val icon: String,
                val medium: String,
                val large: String,
                val contentAwareIcon: String,
                val contentAwareMedium: String,
                val contentAwareLarge: String
        )
    }
}