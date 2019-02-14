package schedule.tutorial.rafagan.androidschedule

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import schedule.tutorial.rafagan.androidschedule.model.Schedule


class ScheduleAdapter: RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>() {
    var items = listOf<Schedule>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_schedule, parent, false)
        return ScheduleHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        val current = items[position]
        holder.bind(current)
    }

    class ScheduleHolder(val v: View) : RecyclerView.ViewHolder(v) {
        private val time = itemView.findViewById<TextView>(R.id.schedule_time)!!
        private val makeSchedule = itemView.findViewById<Button>(R.id.make_schedule)!!

        private lateinit var id: String
        private lateinit var timeStr: String
        private lateinit var dateStr: String

        fun bind(schedule: Schedule) {
            time.text = schedule.time
            id = schedule.placeId
            timeStr = schedule.time
            dateStr = schedule.date

            makeSchedule.setOnClickListener {
                navigateToSchedule()
            }
        }

        fun navigateToSchedule() {
            val job = Intent(v.context, ScheduleActivity::class.java)
            job.putExtra("placeId", id)
            job.putExtra("time", timeStr)
            job.putExtra("date", dateStr)
//            job.putExtra("userId", dateStr)

            val activity = (v.context as Activity)
            activity.startActivityForResult(job, 1)
        }
    }
}