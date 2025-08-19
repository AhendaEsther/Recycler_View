package com.advertise.recyclerview

// Removed: import androidx.privacysandbox.tools.core.generator.build
import androidx.privacysandbox.tools.core.generator.build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor // Make sure you have this dependency
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This defines a singleton object named RetrofitInstance
object RetrofitInstance {

    // Replace this with your actual base URL!
    // Example for jsonplaceholder: "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL = "YOUR_API_BASE_URL_HERE" // <<< IMPORTANT: SET THIS

    private val retrofitClient: Retrofit by lazy {
        // Create a logging interceptor (optional, but very useful for debugging)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
        }

        // Create an OkHttpClient and add the logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Build the Retrofit instance
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Set the custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
            .build()
    }

    // Publicly accessible property to get the ApiInterface implementation
    // This assumes ApiInterface.kt is correctly defined in your project.
    val api: ApiInterface by lazy {
        retrofitClient.create(ApiInterface::class.java)
    }
}