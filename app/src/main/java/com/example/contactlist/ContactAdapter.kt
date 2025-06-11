package com.example.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.databinding.ItemContactBinding

class ContactAdapter(
    private val contacts: List<Contact>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.apply {
            textName.text = contact.name
            textEmail.text = contact.email
            textPhone.text = contact.phone
            imagePhoto.setImageBitmap(contact.photo)
            buttonDelete.setOnClickListener { onDeleteClick(position) }
        }
    }

    override fun getItemCount(): Int = contacts.size
}
