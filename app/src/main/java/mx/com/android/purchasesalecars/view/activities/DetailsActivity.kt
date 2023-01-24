package mx.com.android.purchasesalecars.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.com.android.purchasesalecars.databinding.ActivityDetailsBinding
import mx.com.android.purchasesalecars.model.CarModelDetail
import mx.com.android.purchasesalecars.services.ServiceApi
import mx.com.android.purchasesalecars.services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Detalle del Auto"
        val bundle = intent.extras

        val id = bundle?.getString("id", "0")

        CoroutineScope(Dispatchers.IO).launch {

            val call = RetrofitService.getRetrofit().create(ServiceApi::class.java).getCarDetailApiary(id)

            call.enqueue(object : Callback<CarModelDetail> {
                override fun onResponse(call: Call<CarModelDetail>, response: Response<CarModelDetail>) {
                    binding.apply {

                        tvTitle.text = response.body()?.title
                        tvLongDesc.text = response.body()?.longDesc
                        tvLongDesc1.text = response.body()?.make
                        tvLongDesc2.text = response.body()?.model
                        tvLongDesc3.text = response.body()?.transmission
                        tvLongDesc4.text = response.body()?.cylinders
                        Glide.with(this@DetailsActivity)
                            .load(response.body()?.image)
                            .into(ivImage)
                        pbConexion.visibility = View.INVISIBLE

                    }
                }

                override fun onFailure(call: Call<CarModelDetail>, t: Throwable) {
                    Toast.makeText(this@DetailsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                    binding.pbConexion.visibility = View.INVISIBLE
                }

            })
        }
    }
}