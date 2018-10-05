package schedule.tutorial.rafagan.androidschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import schedule.tutorial.rafagan.androidschedule.model.Job

class JobAdapter: RecyclerView.Adapter<JobAdapter.JobsHolder>() {
    var items = listOf<Job>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_job, parent, false)
        return JobsHolder(itemView)
    }

    override fun onBindViewHolder(holder: JobsHolder, position: Int) {
        val current = items[position]
        holder.bind(current)
    }

    class JobsHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val jobName = itemView.findViewById<TextView>(R.id.job_name)!!

        fun bind(job: Job) {
            jobName.text = job.name
        }
    }
}