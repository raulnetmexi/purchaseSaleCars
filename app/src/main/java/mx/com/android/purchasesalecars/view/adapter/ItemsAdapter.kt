package mx.com.android.purchasesalecars.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import mx.com.android.purchasesalecars.R
import mx.com.android.purchasesalecars.model.CarDataModel
import androidx.databinding.DataBindingUtil
import mx.com.android.purchasesalecars.databinding.CarRowBinding
import mx.com.android.purchasesalecars.view.fragments.ClickListener
import mx.com.android.purchasesalecars.view.viewholder.ItemViewHolder

class ItemsAdapter(private val listener: ClickListener): RecyclerView.Adapter<ItemViewHolder>() {
    private val resource = R.layout.car_row
    lateinit var context: Context
    private val itemList = mutableListOf<CarDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: CarRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.itemSelect(itemList[position])
        }
    }

    fun setItems(list: MutableList<CarDataModel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}