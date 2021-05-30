package com.example.finalprojectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectbatch2.API.APIService
import com.example.finalprojectbatch2.API.ListRespon
import com.example.finalprojectbatch2.model.Model
import com.example.finalprojectbatch2.Adapter.MainAdapter
import com.example.finalprojectbatch2.databinding.ActivityMain2Binding
import com.example.finalprojectbatch2.model.Barang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityMain2Binding
    private lateinit var mainadapter : MainAdapter
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
    private fun BotNav(){
        binding.BotNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Home -> startActivity(Intent(this,MainActivity2::class.java))
                R.id.Plus -> startActivity(Intent(this,MainActivityAdd::class.java))
                R.id.Person -> startActivity(Intent(this,MainActivityPerson::class.java))
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
                        Toast.makeText(applicationContext,"Get Data Success",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListRespon<Barang>>, t: Throwable) {
                println(t.message)
            }


        })
    }
    private fun ShowRcy(model: List<Barang>){
         mainadapter = MainAdapter(model,object : MainAdapter.OnClick{
             override fun ListClick(post: Barang) {
                 startActivity(Intent(this@MainActivity2,ActivityDetailBarang::class.java).apply {
                     putExtra("id",post.id)
                 })


             }

             override fun ImageClick(post: Barang) {
                 startActivity(Intent(this@MainActivity2,MainActivityAdd::class.java).apply {
                     putExtra("id",post.id)
                     putExtra("nama",post.nama)
                     putExtra("kode",post.kode.toString())
                 })
             }

         })
        binding.Rcy.apply {
            adapter = mainadapter
            layoutManager = LinearLayoutManager(this@MainActivity2)
        }

    }

}