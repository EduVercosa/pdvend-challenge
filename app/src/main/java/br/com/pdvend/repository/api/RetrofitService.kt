package br.com.pdvend.repository.api

import br.com.pdvend.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class RetrofitService {

    companion object {

        private const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"

        fun <T : Any> get(type: KClass<T>, baseUrl: String = BuildConfig.REST_SERVICE_URL): T {
            val gson = GsonBuilder()
                    .setDateFormat(
                            DATE_FORMAT
                    )
                    .setPrettyPrinting()
                    .create()

            val okHttpBuilder = OkHttpClient.Builder()
            okHttpBuilder.readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit.create(type.java)
        }

    }
}