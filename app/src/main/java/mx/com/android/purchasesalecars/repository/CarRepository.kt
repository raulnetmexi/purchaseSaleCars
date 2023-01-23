package mx.com.android.purchasesalecars.repository

import mx.com.android.purchasesalecars.services.RetrofitClient
import mx.com.android.purchasesalecars.services.WebService


class CarRepository {
    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    suspend fun getCar() = apiService?.getCar()
}