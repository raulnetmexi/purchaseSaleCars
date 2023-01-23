package mx.com.android.purchasesalecars.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import mx.com.android.purchasesalecars.databinding.FragmentCarDetailBinding
import mx.com.android.purchasesalecars.viewmodels.RecyclerCarViewModel


class CarDetailFragment : Fragment() {

    private var _binding: FragmentCarDetailBinding? = null
    private val binding get() = _binding

    lateinit var viewModel: RecyclerCarViewModel

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
        _binding =  FragmentCarDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemDataSelected?.let { data ->
        ///    Glide.with(requireContext()).load(data.img).into(binding!!.img)
            binding!!.tvName.text = data.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(

        ) = CarDetailFragment()
    }

}