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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        configureScheduleLayout()
    }

    fun configureScheduleLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.schedule_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = SchedulesAdapter()
        adapter.items = listOf(
                Schedule("1", "02:00"),
                Schedule("1", "03:00"),
                Schedule("1", "04:00"),
                Schedule("1", "05:00"),
                Schedule("1", "06:00")
        )
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
