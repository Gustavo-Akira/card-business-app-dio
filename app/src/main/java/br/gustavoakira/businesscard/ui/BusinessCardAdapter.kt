package br.gustavoakira.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.gustavoakira.businesscard.data.BusinessCard
import br.gustavoakira.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter:
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()){
    var listenerShare: (View) -> Unit = {}
    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: BusinessCard){
            binding.tvName.text = item.name
            binding.tvTelephone.text = item.telephone
            binding.tvCompanyName.text = item.company
            binding.tvEmail.text = item.email
            binding.cardContainer.setCardBackgroundColor(Color.parseColor(item.color))
            binding.cardContainer.setOnClickListener {
                listenerShare(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class DiffCallback: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id
    }

}