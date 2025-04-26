package com.fyooo.core.di

import android.app.Application
import androidx.room.Room
import com.fyooo.core.data.BookRepository
import com.fyooo.core.data.api.ApiService
import com.fyooo.core.data.local.database.CartRoomDatabase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    single {
        Room.databaseBuilder(
                get<Application>(),
                CartRoomDatabase::class.java,
                "book_database"
            ).fallbackToDestructiveMigration(false).build()
    }

    single { get<CartRoomDatabase>().cartDao() }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val apiKeyInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("key", "defca25230a8075a3f40d54cc6766697")
                .build()
            chain.proceed(request)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rajaongkir.com/starter/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<BookRepository> {
        BookRepository(get(), get())
    }
}
