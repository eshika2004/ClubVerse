package com.example.loginsignupauth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.CalendarDay
import EventDecorator
import com. prolificinteractive. materialcalendarview. OnDateSelectedListener


class CalenderActivity : AppCompatActivity() {
    private lateinit var calendarView3: CalendarView
    private lateinit var editTextText2: EditText
    private lateinit var databaseReference: DatabaseReference
    private var selectedDate: String = ""

    private val eventDates = HashMap<String, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        calendarView3 = findViewById(R.id.calendarView3)
        editTextText2 = findViewById(R.id.editTextText2)

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")

        // Load event dates and apply decorator
        loadEventDates()

        calendarView3.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = String.format("%04d%02d%02d", year, month + 1, dayOfMonth)
            fetchEventForDate(selectedDate)
        }



    }




    private fun loadEventDates() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val eventDays = HashSet<CalendarDay>()

                for (child in snapshot.children) {
                    val dateStr = child.key ?: continue
                    val year = dateStr.substring(0, 4).toInt()
                    val month = dateStr.substring(4, 6).toInt() - 1 // Fix: Months start from 0
                    val day = dateStr.substring(6, 8).toInt()
                    eventDays.add(CalendarDay.from(year, month, day))
                }

                // Apply the decorator to highlight event dates
                calendarView3.addDecorator(EventDecorator(eventDays))
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseDebug", "Error loading event dates: ${error.message}")
            }
        })
    }




    private fun fetchEventForDate(date: String) {
        databaseReference.child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val event = snapshot.getValue(String::class.java)
                if (event != null) {
                    editTextText2.setText(event)
                    Toast.makeText(this@CalenderActivity, "Event: $event", Toast.LENGTH_SHORT).show()
                } else {
                    editTextText2.setText("")
                    Toast.makeText(this@CalenderActivity, "No event found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CalenderActivity, "Error fetching event!", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun buttonSaveEvent(view: View) {
        val eventText = editTextText2.text.toString()
        if (selectedDate.isNotEmpty()) {
            databaseReference.child(selectedDate).setValue(eventText)
                .addOnSuccessListener {
                    Log.d("Firebase", "Event saved successfully: $eventText")
                    Toast.makeText(this, "Event saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Failed to save event: ${e.message}")
                    Toast.makeText(this, "Failed to save: ${e.message}", Toast.LENGTH_LONG).show()
                }
        } else {
            Toast.makeText(this, "Select a date first!", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun CalendarView.addDecorator(decorator: EventDecorator) {}










