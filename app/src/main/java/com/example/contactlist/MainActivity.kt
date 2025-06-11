package com.example.contactlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactAdapter(contacts) { position ->
            contacts.removeAt(position)
            adapter.notifyItemRemoved(position)
            checkIfEmpty()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        checkIfEmpty()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val contact = data?.getParcelableExtra<Contact>("contact")
            if (contact != null) {
                contacts.add(contact)
                adapter.notifyItemInserted(contacts.size - 1)
                checkIfEmpty()
            }
        }
    }

    private fun checkIfEmpty() {
        binding.textEmpty.visibility = if (contacts.isEmpty()) View.VISIBLE else View.GONE
    }
}

