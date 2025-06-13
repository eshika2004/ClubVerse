package com.example.loginsignupauth

import android.content.SharedPreferences
import android.os.Bundle import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginsignupauth.databinding.ActivityMember2Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MemberActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMember2Binding
    private val membersList = mutableListOf<String>()  // List of members
    private lateinit var adapter: MemberAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMember2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("members_data", MODE_PRIVATE)

        // Load saved members when the activity starts
        loadMembers()

        // Set up the RecyclerView with the Adapter
        adapter = MemberAdapter(membersList) { deletedMember ->
            // Handle member deletion if needed (e.g., save to SharedPreferences)
            saveMembers() // Call saveMembers() to persist changes
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Add Button functionality
        binding.addButton.setOnClickListener {
            showAddMemberDialog()
        }
    }

    private fun showAddMemberDialog() {
        val inputEditText = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add New Member")
            .setView(inputEditText)
            .setPositiveButton("Save") { _, _ ->
                val memberName = inputEditText.text.toString().trim()
                if (memberName.isNotEmpty()) {
                    // Save the new member to the list
                    adapter.addMember(memberName)
                    saveMembers()
                } else {
                    Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }


    // Save members to SharedPreferences
    private fun saveMembers() {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(membersList)
        editor.putString("members", json)
        editor.apply()
    }

    // Load members from SharedPreferences
    private fun loadMembers() {
        val gson = Gson()
        val json = sharedPreferences.getString("members", null)
        val type = object : TypeToken<List<String>>() {}.type
        if (json != null) {
            val savedMembers: List<String> = gson.fromJson(json, type)
            membersList.addAll(savedMembers)
        }
    }

}