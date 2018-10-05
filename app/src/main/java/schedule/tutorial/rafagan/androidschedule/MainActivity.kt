package schedule.tutorial.rafagan.androidschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import schedule.tutorial.rafagan.androidschedule.model.Place

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!Utils.isLogged)
            this.navigateToLogin()

        configurePlacesLayout()
    }

    fun navigateToLogin() {
        val myIntent = Intent(this, LoginActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(myIntent)
    }

    fun configurePlacesLayout() {
        val recyclerView = findViewById<RecyclerView>(R.id.places_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val adapter = PlacesAdapter()
        adapter.items = listOf(
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"),
                Place(
                        "Rei da coxinha",
                        "Melhor marcenaria do Brasil. Aceitamos TeleSena.",
                        "08:00",
                        "23:30"),
                Place(
                        "Padaria do Seu Zé",
                        "Venda de equipamentos eletrônicos, bonecas de porcelanato e tênis para jogging.",
                        "13:00",
                        "15:00"))
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
