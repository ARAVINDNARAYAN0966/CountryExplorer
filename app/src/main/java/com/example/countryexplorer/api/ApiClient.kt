package com.example.countryexplorer.api
import com.example.countryexplorer.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://api.restcountries.com/"
    private const val API_KEY = BuildConfig.REST_COUNTRIES_API_KEY


    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $API_KEY")
                .build()

            chain.proceed(request)
        }
        .build()

    val api: CountryApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryApi::class.java)
}