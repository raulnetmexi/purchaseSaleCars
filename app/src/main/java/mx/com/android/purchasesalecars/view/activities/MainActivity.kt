package mx.com.android.purchasesalecars.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import mx.com.android.purchasesalecars.databinding.ActivityMainBinding

import mx.com.android.purchasesalecars.model.CarModel
import mx.com.android.purchasesalecars.services.ServiceApi

import mx.com.android.purchasesalecars.services.RetrofitService
import mx.com.android.purchasesalecars.util.Constants
import mx.com.android.purchasesalecars.view.adapter.CarAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),CarAdapter.OnCarsClickListener  {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Autos en Venta"
        CoroutineScope(Dispatchers.IO).launch {

            Log.d(Constants.LOGTAG, "Hilo al iniciar la corrutina: ${Thread.currentThread().name}")

            val call =
                RetrofitService.getRetrofit().create(ServiceApi::class.java).getCars()  //Con Apiary

            call.enqueue(object : Callback<ArrayList<CarModel>> {
                override fun onResponse(
                    call: Call<ArrayList<CarModel>>,
                    response: Response<ArrayList<CarModel>>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")
                    Log.d(Constants.LOGTAG, "Hilo en el onResponse: ${Thread.currentThread().name}")

                    binding.pbConexion.visibility = View.GONE
                    binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMenu.adapter=CarAdapter(response.body()!!,this@MainActivity)
                }

                override fun onFailure(call: Call<ArrayList<CarModel>>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "No hay conexión. Error: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.pbConexion.visibility = View.GONE
                }
            })

        }

    }
    override fun onCarClick(car: CarModel) {
        val parametros = Bundle().apply {
            putString("id", car.id)
        }
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtras(parametros)
        }
        startActivity(intent)
    }

}