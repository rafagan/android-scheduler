package schedule.tutorial.rafagan.androidschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import schedule.tutorial.rafagan.androidschedule.model.Schedule

class SchedulesAdapter: RecyclerView.Adapter<SchedulesAdapter.ScheduleHolder>() {
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

    class ScheduleHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val time = itemView.findViewById<TextView>(R.id.schedule_time)!!

        fun bind(schedule: Schedule) {
            time.text = schedule.time
        }
    }
}