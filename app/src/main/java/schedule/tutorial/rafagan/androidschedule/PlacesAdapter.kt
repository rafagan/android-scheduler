package schedule.tutorial.rafagan.androidschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import schedule.tutorial.rafagan.androidschedule.model.Place

class PlacesAdapter: RecyclerView.Adapter<PlacesAdapter.PlacesHolder>() {
    var items = listOf<Place>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_place, parent, false)
        return PlacesHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlacesHolder, position: Int) {
        val current = items[position]
        holder.bind(current)
    }

    class PlacesHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val name = itemView.findViewById<TextView>(R.id.place_name)!!
        private val description = itemView.findViewById<TextView>(R.id.place_description)!!
        private val openTime = itemView.findViewById<TextView>(R.id.place_open_time)!!
        private val closeTime = itemView.findViewById<TextView>(R.id.place_close_time)!!

        fun bind(place: Place) {
            name.text = place.name
            description.text = place.description
            openTime.text = place.openTime
            closeTime.text = place.closeTime
        }
    }
}