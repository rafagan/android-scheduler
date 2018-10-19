package schedule.tutorial.rafagan.androidschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import schedule.tutorial.rafagan.androidschedule.model.Schedule

class PlaceActivity : AppCompatActivity() {
    private val adapter = SchedulesAdapter()
    private lateinit var placeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        val bundle = intent.extras
        placeId = bundle.getString("placeId")
        configureScheduleLayout()
    }

    fun loadSchedules() {
        val list = mutableListOf<Schedule>()
        for (i in 0..23) {
            val leftZero = if(i < 10) "0" else ""
            list.add(Schedule("1", "$leftZero$i:00"))
        }

        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun configureScheduleLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.schedule_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter.items = listOf()
        loadSchedules()
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    fun navigateToSchedule() {
        val schedule = Intent(this, ScheduleActivity::class.java)
        startActivity(schedule)
    }

    fun makeSchedule(view: View) {
        this.navigateToSchedule()
    }
}
