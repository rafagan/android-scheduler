package schedule.tutorial.rafagan.androidschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import schedule.tutorial.rafagan.androidschedule.model.Job

class ScheduleActivity : AppCompatActivity() {
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

    fun configureJob() {
        val recyclerView = findViewById<RecyclerView>(R.id.job_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = JobAdapter()
        adapter.items = listOf(
                Job("1","1","Pintura"),
                Job("1","2","Dança do ventre")
        )
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
