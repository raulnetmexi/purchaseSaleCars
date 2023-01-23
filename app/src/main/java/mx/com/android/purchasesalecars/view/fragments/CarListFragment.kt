package mx.com.android.purchasesalecars.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.com.android.purchasesalecars.R
import mx.com.android.purchasesalecars.databinding.FragmentCarListBinding
import mx.com.android.purchasesalecars.model.CarDataModel
import mx.com.android.purchasesalecars.view.adapter.ItemsAdapter
import mx.com.android.purchasesalecars.viewmodels.RecyclerCarViewModel


class CarListFragment : Fragment(), ClickListener {
    lateinit var viewModel: RecyclerCarViewModel
    lateinit var binding: FragmentCarListBinding
    private var mAdapter: ItemsAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it).get(RecyclerCarViewModel::class.java)
            }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_list, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // inicializa recyclerview
        mAdapter = ItemsAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = mAdapter

        //observador de la lista
        viewModel.listState.observe(viewLifecycleOwner) {
            mAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }

        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }

        //mAdapter?.setItems(list)
        viewModel.fetchCarData()

    }

    override fun itemSelect(data: CarDataModel) {
        viewModel.setItemSelection(data)

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(android.R.id.content, CarDetailFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }


}

interface ClickListener {
    fun itemSelect(data: CarDataModel)
}