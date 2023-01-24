package mx.com.android.purchasesalecars.model

import com.google.gson.annotations.SerializedName

/**
 * Creado por Raul Oropeza el 23/01/23
 */
data class CarModel(
    @SerializedName("id_car")
    var id: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("make")
    var make: String? = null,

    @SerializedName("fuel_type")
    var fuel_type: String? = null,

    @SerializedName("model")
    var model: String? = null,

    @SerializedName("cylinders")
    var cylinders: String? = null,

    @SerializedName("transmission")
    var transmission: String? = null,

    @SerializedName("year")
    var year: String? = null,

    @SerializedName("image")
    var thumbnail: String? = null


)
