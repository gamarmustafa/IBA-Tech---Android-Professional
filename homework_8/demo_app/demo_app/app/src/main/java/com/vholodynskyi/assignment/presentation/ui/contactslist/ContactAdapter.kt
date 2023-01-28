package com.vholodynskyi.assignment.presentation.ui.contactslist

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vholodynskyi.assignment.R
import com.vholodynskyi.assignment.data.remote.api.contacts.ApiContact
import com.vholodynskyi.assignment.databinding.ItemContactListBinding

class ContactAdapter(
    private val context: Activity,
    private val onItemClicked: ItemClick
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            ItemContactListBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = listDiffer.currentList[position]
        with(holder.binding) {
            if (contact.name?.firstName != null && contact.email != null) {
                tvFullName.text = "${contact.name.firstName} ${contact.name.lastName}"
                tvEmail.text = contact.email
            }
            if (contact.picture != null) {
                Glide.with(context).load(contact.picture.thumbnail)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivPicture)
            }
            root.setOnClickListener {
                onItemClicked(holder.absoluteAdapterPosition)
            }
        }
    }
    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<ApiContact>() {
        override fun areItemsTheSame(oldItem: ApiContact, newItem: ApiContact): Boolean {
            return oldItem.picture == newItem.picture
        }

        override fun areContentsTheSame(oldItem: ApiContact, newItem: ApiContact): Boolean {
            return oldItem == newItem
        }
    }
    val listDiffer = AsyncListDiffer(this, diffUtil)
}

class ViewHolder(val binding: ItemContactListBinding) : RecyclerView.ViewHolder(binding.root)

typealias ItemClick = (Int) -> Unit