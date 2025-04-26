package com.fyooo.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_book")
data class CartBookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long?,

    @ColumnInfo(name = "bookId")
    var bookId: Long?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "price")
    var price: Int?,

    @ColumnInfo(name = "coverUrl")
    var coverUrl: String?,

    @ColumnInfo(name = "quantity")
    var quantity: Int?,

)