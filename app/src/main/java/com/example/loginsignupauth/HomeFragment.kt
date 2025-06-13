package com.example.loginsignupauth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var entertainmentclub: CardView
    private lateinit var sportclub: CardView
    private lateinit var debateactivity: CardView
    private lateinit var culturalclub: CardView
    private lateinit var filmclub: CardView
    private lateinit var singingclub: CardView
    private lateinit var yogaclub: CardView
    private lateinit var artclub: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize CardViews
        entertainmentclub = view.findViewById(R.id.entertainmentclub)
        sportclub = view.findViewById(R.id.sportclub)
        debateactivity = view.findViewById(R.id.debateactivity)
        culturalclub = view.findViewById(R.id.culturalclub)
        filmclub = view.findViewById(R.id.filmclub)
        singingclub = view.findViewById(R.id.singingclub)
        yogaclub = view.findViewById(R.id.yogaclub)
        artclub = view.findViewById(R.id.artclub)

        // Set click listeners for each card
        entertainmentclub.setOnClickListener {

            val intent = Intent(requireContext(), EntertainmentActivity::class.java)
            startActivity(intent)
        }

        sportclub.setOnClickListener {

            val intent = Intent(requireContext(), SportsActivity::class.java)
            startActivity(intent)
        }

        debateactivity.setOnClickListener {

            val intent = Intent(requireContext(), DebateActivity::class.java)
            startActivity(intent)
        }

        culturalclub.setOnClickListener {

            val intent = Intent(requireContext(), CulturalActivity::class.java)
            startActivity(intent)
        }

        filmclub.setOnClickListener {

            val intent = Intent(requireContext(), FilmActivity::class.java)
            startActivity(intent)
        }

        singingclub.setOnClickListener {

            val intent = Intent(requireContext(), SingingActivity::class.java)
            startActivity(intent)
        }

        yogaclub.setOnClickListener {

            val intent = Intent(requireContext(), YogaActivity::class.java)
            startActivity(intent)
        }

        artclub.setOnClickListener {
          
            val intent = Intent(requireContext(), ArtActivity::class.java)
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
