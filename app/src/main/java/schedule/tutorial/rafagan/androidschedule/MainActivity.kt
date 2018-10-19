package schedule.tutorial.rafagan.androidschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import schedule.tutorial.rafagan.androidschedule.firebase.Auth
import schedule.tutorial.rafagan.androidschedule.model.Place

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    fun navigateToPlace() {
        val place = Intent(this, PlaceActivity::class.java)
        startActivity(place)
    }

    fun configurePlacesLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.places_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = PlacesAdapter()
        adapter.items = listOf(
                Place(
                        "1",
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "2",
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "3",
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "4",
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "5",
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"))
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    fun seeTimes(view: View) {
        this.navigateToPlace()
    }
}
