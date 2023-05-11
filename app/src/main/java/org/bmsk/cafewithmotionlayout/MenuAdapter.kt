package org.bmsk.cafewithmotionlayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.bmsk.cafewithmotionlayout.data.model.MenuItem
import org.bmsk.cafewithmotionlayout.databinding.ItemMenuRowBinding

class MenuAdapter(
    // itemClick
): ListAdapter<MenuItem, MenuAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemMenuRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuItem) {
            binding.nameTextView.text = item.name
            Glide.with(binding.imageView)
                .load(item.image)
                .circleCrop()
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMenuRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem === newItem // hash 값 비교 (이름으로 구분하는 것은 동일한 메뉴가 있을 수 있기 때문에 hash 값 비교로 처리)
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}