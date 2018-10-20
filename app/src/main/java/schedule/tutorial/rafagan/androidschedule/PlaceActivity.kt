package schedule.tutorial.rafagan.androidschedule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.ProgressBar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import schedule.tutorial.rafagan.androidschedule.firebase.Database
import schedule.tutorial.rafagan.androidschedule.model.Schedule
import schedule.tutorial.rafagan.androidschedule.model.fromMapToSchedule
import java.text.SimpleDateFormat
import java.util.*


class PlaceActivity : AppCompatActivity() {
    private val adapter = ScheduleAdapter()
    private lateinit var placeId: String
    private lateinit var calendar: CalendarView
    private var timeMilliseconds: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        calendar = findViewById(R.id.schedule_day)
        val bundle = intent.extras
        placeId = bundle.getString("placeId")

        val calendar = findViewById<CalendarView>(R.id.schedule_day)
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val c = Calendar.getInstance()
            c.set(year, month, dayOfMonth)
            timeMilliseconds = c.time.time
            loadSchedules()
        }

        configureScheduleLayout()
    }

    @SuppressLint("SimpleDateFormat")
    fun generateDate(timeMilliseconds: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(Date(timeMilliseconds))
    }

    fun loadSchedules() {
        if(timeMilliseconds == null) return

        val dateStr = generateDate(timeMilliseconds!!)
        val loading = findViewById<ProgressBar>(R.id.places_loading)
        loading.visibility = View.VISIBLE

        val list = mutableListOf<Schedule>()
        adapter.items = list
        adapter.notifyDataSetChanged()

        Database.createConnection()
                .child("database")
                .child("places")
                .child(placeId)
                .child("schedules")
                .child(dateStr)
                .addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.d("Database error", p0.toString())
                        loading.visibility = View.INVISIBLE
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val map = mutableMapOf<String, Schedule>()
                        if(p0.exists()){
                            p0.children.forEach {
                                @Suppress("UNCHECKED_CAST")
                                val schedule = fromMapToSchedule(it.value as HashMap<String, String>)
                                map[schedule.time] = schedule
                            }
                        }

                        for (i in 0..23) {
                            val leftZero = if(i < 10) "0" else ""
                            val time = "$leftZero$i:00"
                            if(map[time] != null) continue
                            list.add(Schedule(placeId, dateStr, "$leftZero$i:00"))
                        }

                        adapter.items = list
                        adapter.notifyDataSetChanged()
                        loading.visibility = View.INVISIBLE
                    }
                })
    }

    fun configureScheduleLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.schedule_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter.items = listOf()
        timeMilliseconds = calendar.date
        loadSchedules()
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) loadSchedules()
    }
}
