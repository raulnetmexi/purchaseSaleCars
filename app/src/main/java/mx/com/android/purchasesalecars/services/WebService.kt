package mx.com.android.purchasesalecars.services


import mx.com.android.purchasesalecars.model.CarResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("pokedex.json")

    suspend fun getCar(): Response<CarResponse>
}