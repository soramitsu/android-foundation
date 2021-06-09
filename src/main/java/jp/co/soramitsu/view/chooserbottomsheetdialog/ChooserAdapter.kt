package jp.co.soramitsu.view.chooserbottomsheetdialog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.co.soramitsu.common.R

class ChooserAdapter : ListAdapter<ChooserItem, ChooserViewHolder>(DiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_chooser
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ChooserViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_chooser, viewGroup, false)

        return ChooserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ChooserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.chooserItemText)

    fun bind(item: ChooserItem) {
        title.setText(item.title)
        title.setCompoundDrawablesWithIntrinsicBounds(item.icon, 0, 0, 0)
        title.setOnClickListener { item.clickHandler() }
    }
}

object DiffCallback : DiffUtil.ItemCallback<ChooserItem>() {
    override fun areItemsTheSame(oldItem: ChooserItem, newItem: ChooserItem): Boolean {
        return when {
            oldItem is ChooserItem && newItem is ChooserItem -> oldItem.title == newItem.title
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ChooserItem, newItem: ChooserItem): Boolean {
        return when {
            oldItem is ChooserItem && newItem is ChooserItem -> oldItem == newItem
            else -> false
        }
    }
}