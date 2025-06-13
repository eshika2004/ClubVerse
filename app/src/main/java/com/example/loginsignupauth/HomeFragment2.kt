package com.example.loginsignupauth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import com.google.android.material.snackbar.Snackbar

class HomeFragment2 : Fragment() {
    private lateinit var calender: CardView
    private lateinit var sportclub: CardView
    private lateinit var attendance: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home2, container, false)


        calender = view.findViewById<CardView>(R.id.calender)
        attendance = view.findViewById<CardView>(R.id.attendance)
        sportclub = view.findViewById<CardView>(R.id.sportclub)

        calender.setOnClickListener {

            val intent = Intent(requireContext(), CalendarActivity2::class.java)
            startActivity(intent)
        }

        sportclub.setOnClickListener {

            val intent = Intent(requireContext(), MemberActivity2::class.java)
            startActivity(intent)
        }

        attendance.setOnClickListener {

            val intent = Intent(requireContext(), AttendanceActivity::class.java)
            startActivity(intent)
        }
        return view
    }
    private fun showSnackbar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}
