package com.fyooo.fybook.data

data class Book (
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val coverUrl: String,
    val rating: Float,
    val publishedDate: String,
    val publisher: String,
    val language: String,
    val categories: String,
);