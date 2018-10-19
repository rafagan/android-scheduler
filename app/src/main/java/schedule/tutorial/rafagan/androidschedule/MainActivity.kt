package schedule.tutorial.rafagan.androidschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import schedule.tutorial.rafagan.androidschedule.firebase.Auth
import schedule.tutorial.rafagan.androidschedule.firebase.Database
import schedule.tutorial.rafagan.androidschedule.firebase.generateDatabase
import schedule.tutorial.rafagan.androidschedule.model.Place
import schedule.tutorial.rafagan.androidschedule.model.fromMapToPlace

class MainActivity : AppCompatActivity() {
    val adapter = PlacesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        generateDatabase()

        if(!Auth.isLoggedIn()) this.navigateToLogin()
        configurePlacesLayout()

        val logout = findViewById<Button>(R.id.btn_logout)
        logout.setOnClickListener {
            Auth.logout()
            navigateToLogin()
        }
    }

    fun navigateToLogin() {
        val myIntent = Intent(this, LoginActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(myIntent)
    }

    fun loadPlaces() {
        val loading = findViewById<ProgressBar>(R.id.mainLoading)
        loading.visibility = View.VISIBLE

        Database.createConnection()
                .child("database")
                .child("places").addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.d("Database error", p0.toString())
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(!p0.exists()) return

                        val list = mutableListOf<Place>()
                        p0.children.forEach {
                            @Suppress("UNCHECKED_CAST")
                            list.add(fromMapToPlace(it.value as HashMap<String, String>))
                        }
                        adapter.items = list
                        adapter.notifyDataSetChanged()
                        loading.visibility = View.INVISIBLE
                    }
                })
    }

    fun configurePlacesLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.places_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        loadPlaces()
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
