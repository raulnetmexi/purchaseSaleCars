package mx.com.android.purchasesalecars.model

data class CarResponse(
    val car: MutableList<CarDataModel> = mutableListOf()
)
data class CarDataModel (
     val id: Long,
     val name: String = "",
     val img: String
)

