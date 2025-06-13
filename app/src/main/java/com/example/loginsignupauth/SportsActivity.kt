package com.example.loginsignupauth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignupauth.EntertainmentActivity

class SportsActivity : AppCompatActivity() {
    private lateinit var openCalendarButton: CardView
    private lateinit var openmemberButton: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)


        openCalendarButton = findViewById<CardView>(R.id.openCalendarButton)
        openmemberButton=findViewById<CardView>(R.id.openmemberButton)


        openCalendarButton.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@SportsActivity,CalenderActivity::class.java)
            startActivity(intent)
        })


        openmemberButton.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@SportsActivity,MemberActivity::class.java)
            startActivity(intent)
        })




    }
}