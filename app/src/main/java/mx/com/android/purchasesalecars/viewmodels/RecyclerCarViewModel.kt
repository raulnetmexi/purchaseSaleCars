package mx.com.android.purchasesalecars.viewmodels

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mx.com.android.purchasesalecars.model.CarDataModel
import mx.com.android.purchasesalecars.repository.CarRepository
import kotlin.coroutines.CoroutineContext

class RecyclerCarViewModel(app: Application): AndroidViewModel(app),CoroutineScope {

    private val _itemSelected = MutableLiveData<CarDataModel>()
    var itemDataSelected: CarDataModel? = null

    private val _listState = MutableLiveData<MutableList<CarDataModel>>()
    var listState: LiveData<MutableList<CarDataModel>> = _listState

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = CarRepository()
    lateinit var observerOnCategorySelected: Observer<CarDataModel>

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default

    init {
        initObserver()
    }

    private fun initObserver() {
        observerOnCategorySelected = Observer { value ->
            value.let {
                _itemSelected.value = it
            }
        }
    }



    fun setItemSelection(item: CarDataModel) {
        itemDataSelected = item
    }

    fun fetchCarData() {
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getCar()
            response?.body()?.car.let { list->
                _listState.value = list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // Memory leak

}