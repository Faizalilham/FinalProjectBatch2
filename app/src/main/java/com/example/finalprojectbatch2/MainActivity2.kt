package com.example.finalprojectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectbatch2.API.APIService
import com.example.finalprojectbatch2.API.ListRespon
import com.example.finalprojectbatch2.Adapter.MainAdapter
import com.example.finalprojectbatch2.databinding.ActivityMain2Binding
import com.example.finalprojectbatch2.model.Barang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityMain2Binding
    private lateinit var mainadapter : MainAdapter
    private var data : MutableList<Barang> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        BotNav()

    }

    override fun onResume() {
        super.onResume()
        GetBarang()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    private fun BotNav(){
        binding.BotNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Plus -> startActivity(Intent(this,MainActivityAdd::class.java).also { finish() })
                R.id.Person -> startActivity(Intent(this,MainActivityPerson::class.java).also { finish() })
            }
            true
        }
    }
    private fun GetBarang(){
        APIService.APIEndPoint().GetBarang().enqueue(object : Callback<ListRespon<Barang>>{
            override fun onResponse(call: Call<ListRespon<Barang>>, response: Response<ListRespon<Barang>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        ShowRcy(body.data)
                        searchview(body.data)
                        Toast.makeText(applicationContext,"Get the item successfully",Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<ListRespon<Barang>>, t: Throwable) {
                println(t.message)
            }


        })
    }
    private fun ShowRcy(model: MutableList<Barang>){
        data.addAll(model)
         mainadapter = MainAdapter(data,object : MainAdapter.OnClick{
             override fun ListClick(post: Barang) {
                 startActivity(Intent(this@MainActivity2,ActivityDetailBarang::class.java).apply {
                     putExtra("id",post.id)
                 })
                finish()

             }

             override fun ImageClick(post: Barang) {
                 startActivity(Intent(this@MainActivity2,MainActivityAdd::class.java).apply {
                     putExtra("id",post.id)
                     putExtra("nama",post.nama)
                     putExtra("kode",post.kode.toString())
                 })
                 finish()
             }

         })
        binding.Rcy.apply {
            adapter = mainadapter
            layoutManager = LinearLayoutManager(this@MainActivity2)
        }


//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                mainadapter.filter.filter(query)
//                mainadapter.notifyDataSetChanged()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                mainadapter.filter.filter(newText)
//                mainadapter.notifyDataSetChanged()
//                return true
//            }
//
//        })

    }


    private fun searchview(listmodel : MutableList<Barang>){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    val search = newText.toLowerCase(Locale.getDefault())
                    data.clear()
                    for(i in listmodel){
                        if(i.nama.toLowerCase(Locale.getDefault()).contains(search)){
                            data.add(i)
                        }
                        binding.Rcy.adapter!!.notifyDataSetChanged()
                    }
                }else{
                    data.clear()
                    data.addAll(listmodel)
                    binding.Rcy.adapter!!.notifyDataSetChanged()

                }
                return true
            }

        })
    }

}