package mx.com.android.purchasesalecars.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import mx.com.android.purchasesalecars.databinding.CarRowBinding
import mx.com.android.purchasesalecars.model.CarDataModel


class ItemViewHolder(binding: CarRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: CarRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: CarDataModel) {
        binding?.let { view->
            view.name = model.name
          //  Glide.with(view.root.context).load(model.img).into(view.imgCharacter)
        }
    }
}