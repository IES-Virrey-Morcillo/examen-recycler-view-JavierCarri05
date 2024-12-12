package com.iesvirreymorcillo.rvmontes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.iesvirreymorcillo.rvmontes.Adapter.MontesAdapter
import com.iesvirreymorcillo.rvmontes.FuncionJSON.getJsonFromAssets
import com.iesvirreymorcillo.rvmontes.databinding.ActivityRecyclerViewMontesBinding

class RecyclerViewMontes : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewMontesBinding
    private lateinit var copyList: MutableList<Montes>
    private lateinit var montesmutablelist: MutableList<Montes>
    private lateinit var adapter: MontesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_recycler_view_montes)
        binding = ActivityRecyclerViewMontesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initRecyclerView()
        retrieveMonte()


        binding.svMonte.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val filteredList: MutableList<Montes> =
                        copyList.filter { it.nombre.lowercase().contains(newText.lowercase()) }.toMutableList()
                    adapter.filterByName(filteredList)
                }
                return false
            }
        })
    }



    private fun initRecyclerView() {
        montesmutablelist = getListFromJson().toMutableList()
        adapter = MontesAdapter(
            montesList = montesmutablelist,
            onClickDelete = { position -> onDeleteItem(position)},
            onClickListener = { monte -> onItemSelected(monte) }
        )
        val manager = LinearLayoutManager(this)
        binding.rvMontes.layoutManager = manager
        binding.rvMontes.adapter = adapter
        binding.btnAddMonte.setOnClickListener{ crearMonte() }
    }

    //funcion ver mas
    private fun onItemSelected(monte: Montes) {
        Toast.makeText(this, monte.urlinfo, Toast.LENGTH_SHORT).show()
    }

    //Funcion Borrar
    private fun onDeleteItem(position: Int) {
        montesmutablelist.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    //funcion añadir
    private fun retrieveMonte() {
        val bundle = intent.extras
        if(bundle != null){
            val monteN = bundle.getString("nombre")
            val alturaM = bundle.getString("altura")
            val urlMonte = bundle.getString("urlmonte")
            val contineneteM = bundle.getString("continente")
            val urlinfoM = bundle.getString("urlinfo")
            val monte = Montes( (monteN.toString()), (urlMonte.toString()),
                (alturaM.toString()), (contineneteM.toString()), (urlinfoM.toString()))
            montesmutablelist.add(monte)
            adapter.notifyItemInserted(montesmutablelist.size - 1)
        }
    }
    private fun crearMonte() {
        val intent = Intent(this, AddMontesActivity::class.java)
        startActivity(intent)
    }

    //Funcion JSON
    fun getListFromJson(): MutableList<Montes> {
        val json: String? = getJsonFromAssets(this, "montes.json")
        val montesList = Gson().fromJson(json, Array<Montes>::class.java).toMutableList()
        //Esta copia la utilizaremos para el SearchView posterior
        copyList = montesList
        return montesList
    }
}