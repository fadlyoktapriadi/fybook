package com.fyooo.fybook.di

import com.fyooo.fybook.data.BookRepository
import com.fyooo.fybook.ui.screen.Home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val databaseModule = module {
//    factory {
//        get<UserRoomDatabase>().usersDao()
//    }
//    single {
//        val passphrase: ByteArray = SQLiteDatabase.getBytes("fadly".toCharArray())
//        val factory = SupportFactory(passphrase)
//        Room.databaseBuilder(
//            androidContext(),
//            UserRoomDatabase::class.java, "Users"
//        ).fallbackToDestructiveMigration()
//            .openHelperFactory(factory)
//            .build()
//    }
//}
//
//val networkModule = module {
//    single {
//        val hostname = "api.github.com"
//        val certificatePinner = CertificatePinner.Builder()
//            .add(hostname, "sha256/lmo8/KPXoMsxI+J9hY+ibNm2r0IYChmOsF9BxD74PVc=")
//            .add(hostname, "sha256/6YBE8kK4d5J1qu1wEjyoKqzEIvyRY5HyM/NB2wKdcZo=")
//            .add(hostname, "sha256/ICGRfpgmOUXIWcQ/HXPLQTkFPEFPoDyjvH7ohhQpjzs=")
//            .build()
//        OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val original = chain.request()
//                val requestBuilder = original.newBuilder()
//                    .header("Authorization", API_KEY)
//                val request = requestBuilder.build()
//                chain.proceed(request)
//            }
//            .connectTimeout(1, TimeUnit.MINUTES)
//            .readTimeout(1, TimeUnit.MINUTES)
//            .certificatePinner(certificatePinner)
//            .build()
//    }
//
//    single {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(get())
//            .build()
//        retrofit.create(ApiService::class.java)
//    }
//}

val repositoryModule = module {
    single<BookRepository> {
        BookRepository()
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}