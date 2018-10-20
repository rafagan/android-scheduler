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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import schedule.tutorial.rafagan.androidschedule.firebase.Database
import schedule.tutorial.rafagan.androidschedule.model.Job
import schedule.tutorial.rafagan.androidschedule.model.fromMapToJob

class ScheduleActivity : AppCompatActivity() {
    private val adapter = JobAdapter()
    private lateinit var placeId: String
    private lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val bundle = intent.extras
        placeId = bundle.getString("placeId")
        time = bundle.getString("time")

        findViewById<TextView>(R.id.selected_time).text = time

        configureJob()
    }

    fun loadJobs() {
        val loading = findViewById<ProgressBar>(R.id.job_loading)
        loading.visibility = View.VISIBLE

        Database.createConnection()
                .child("database")
                .child("places")
                .child(placeId)
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
}
