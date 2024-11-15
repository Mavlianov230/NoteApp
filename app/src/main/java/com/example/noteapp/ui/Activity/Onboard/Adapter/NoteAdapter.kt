package com.example.noteapp.ui.Activity.Onboard.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.ui.Activity.Data.Daos.Models.NoteModels
import com.example.noteapp.ui.Activity.Interface.OnClickItem
import kotlinx.coroutines.NonDisposableHandle.parent

class NoteAdapter (
    private val onLongClick: OnClickItem,
    private val onClick: OnClickItem
) : ListAdapter<NoteModels, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NoteModels?) {
            binding.itemTitle.text = item?.title
            binding.itemDescription.text = item?.description
            binding.itemDate.text = item?.date
            binding.itemTime.text = item?.time
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }

        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModels>() {
        override fun areItemsTheSame(oldItem: NoteModels, newItem: NoteModels): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModels, newItem: NoteModels): Boolean {
            return oldItem == newItem
        }
    }
}