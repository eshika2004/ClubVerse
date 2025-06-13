package com.example.loginsignupauth

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(
    private val members: MutableList<String>,
    private val onMemberDeleted: (String) -> Unit // Callback for member deletion
) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1) // Use the built-in TextView ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) // Simple built-in layout
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        // Set member name with numbering
        holder.nameTextView.text = "${position + 1}. ${members[position]}"
        holder.nameTextView.setTextColor(android.graphics.Color.BLACK)  // Set text color to black
        holder.nameTextView.setTypeface(holder.nameTextView.typeface, android.graphics.Typeface.BOLD) // Set text to bold

        // Set an OnClickListener to handle member deletion
        holder.itemView.setOnClickListener {
            showDeleteConfirmationDialog(holder.itemView, members[position], position)
        }
    }
    override fun getItemCount(): Int = members.size

    fun addMember(name: String) {
        members.add(name)
        // Debugging line: Log the added name
        Log.d("MemberAdapter", "Added member: $name")
        notifyItemInserted(members.size - 1)  // Notify RecyclerView that data has changed
    }

    private fun showDeleteConfirmationDialog(view: View, memberName: String, position: Int) {
        AlertDialog.Builder(view.context)
            .setTitle("Delete Member")
            .setMessage("Are you sure you want to delete $memberName?")
            .setPositiveButton("Yes") { _, _ ->
                deleteMember(position)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun deleteMember(position: Int) {
        val memberName = members[position]
        members.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, members.size)
        Log.d("MemberAdapter", "Deleted member: $memberName")
        onMemberDeleted(memberName) // Call the callback to notify deletion
    }
}