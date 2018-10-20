package schedule.tutorial.rafagan.androidschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import schedule.tutorial.rafagan.androidschedule.firebase.Database
import schedule.tutorial.rafagan.androidschedule.model.*

class ScheduleActivity : AppCompatActivity() {
    private val adapter = JobAdapter()
    private lateinit var schedule: Schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val bundle = intent.extras
        schedule = Schedule(
                bundle.getString("placeId"),
                bundle.getString("date"),
                bundle.getString("time")
        )

        findViewById<TextView>(R.id.selected_time).text = schedule.time

        configureJob()
    }

    fun loadJobs() {
        val loading = findViewById<ProgressBar>(R.id.job_loading)
        loading.visibility = View.VISIBLE

        Database.createConnection()
                .child("database")
                .child("places")
                .child(schedule.placeId)
                .child("jobs")
                .addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.d("Database error", p0.toString())
                        loading.visibility = View.INVISIBLE
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(!p0.exists()){
                            loading.visibility = View.INVISIBLE
                            return
                        }

                        val list = mutableListOf<Job>()
                        p0.children.forEach {
                            @Suppress("UNCHECKED_CAST")
                            list.add(fromMapToJob(it.value as HashMap<String, String>))
                        }
                        adapter.items = list
                        adapter.notifyDataSetChanged()
                        loading.visibility = View.INVISIBLE
                    }
                })
    }

    fun configureJob() {
        val recyclerView = findViewById<RecyclerView>(R.id.job_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter.items = listOf()
        loadJobs()
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    fun scheduleJobs(view: View) {
        val loading = findViewById<ProgressBar>(R.id.job_loading)
        loading.visibility = View.VISIBLE

        val jobs = mutableListOf<Map<String, String>>()
        for(job in adapter.items.filter { it.isChecked }) {
            jobs.add(fromJobToMapToSchedule(job))
        }

        val data = mutableMapOf<String, Any>()
        data.putAll(fromScheduleToMap(schedule))
        data["jobs"] = jobs

        Database.createConnection()
                .child("database")
                .child("places")
                .child(schedule.placeId)
                .child("schedules")
                .child(schedule.date)
                .child(schedule.time)
                .setValue(data)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Salvo!", Toast.LENGTH_SHORT).show()
                    loading.visibility = View.INVISIBLE
                }
    }
}
