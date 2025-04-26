package com.fyooo.core.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fyooo.core.data.local.entity.CartBookEntity

@Dao
interface CartDao {

     @Insert
     suspend fun insertCartBook(cartBook: CartBookEntity)

     @Query("SELECT * FROM cart_book")
     suspend fun getAllCartBooks(): List<CartBookEntity>

     @Delete
     suspend fun deleteCartBook(cartBook: CartBookEntity)

    @Query("UPDATE cart_book SET quantity = :newQuantity WHERE id = :id")
    suspend fun updateCartBookQuantity(id: Long, newQuantity: Int)

}