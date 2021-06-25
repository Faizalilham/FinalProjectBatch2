package com.example.finalprojectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalprojectbatch2.API.APIService
import com.example.finalprojectbatch2.API.ListRespon
import com.example.finalprojectbatch2.model.Model
import com.example.finalprojectbatch2.API.SingleRespon
import com.example.finalprojectbatch2.databinding.ActivityMainAddBinding
import com.example.finalprojectbatch2.model.Barang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityAdd : AppCompatActivity() {
    private lateinit var binding : ActivityMainAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ButtonAdd()
        getdata()
        ButtonUpdate()
        ButtonBack()
    }
    private fun AddBarang(){
        val nama = binding.ETNamaBarang.text.toString()
        val kode = binding.ETKode.text.toString()

        APIService.APIEndPoint().PostBarang(nama,kode.toInt()).enqueue(object : Callback<SingleRespon<Barang>>{
            override fun onResponse(call: Call<SingleRespon<Barang>>, response: Response<SingleRespon<Barang>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    Toast.makeText(applicationContext,"Post Barang Success",Toast.LENGTH_SHORT).show()
                    println("Success Post Barang "+body)
                }else{
                    Toast.makeText(applicationContext,"Error Post Barang",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SingleRespon<Barang>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun ButtonAdd(){
        binding.ButtonSave.setOnClickListener {
            AddBarang()
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }

    private fun getdata(){

        binding.ETNamaBarang.setText(intent.getStringExtra("nama"))
        binding.ETKode.setText(intent.getStringExtra("kode"))
    }
    private fun UpdateBarang(){
        val id = intent.getIntExtra("id",1)
        val nama = binding.ETNamaBarang.text.toString()
        val kode = binding.ETKode.text.toString().toInt()
        APIService.APIEndPoint().UpdateBarang(id, nama, kode).enqueue(object : Callback<SingleRespon<Barang>>{
            override fun onResponse(call: Call<SingleRespon<Barang>>, response: Response<SingleRespon<Barang>>) {
                if(response.isSuccessful){
                    val body = response
                    Toast.makeText(applicationContext,"Update Barang Success",Toast.LENGTH_SHORT).show()
                    println("Success Update Barang "+body)
                }
            }

            override fun onFailure(call: Call<SingleRespon<Barang>>, t: Throwable) {
                println(t.message)
            }
        })
    }
    private fun ButtonUpdate(){
        binding.ButtonnUpdate.setOnClickListener {
            UpdateBarang()
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }
    private fun ButtonBack(){
        binding.backtoearly.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java).also {
                finish()
            })

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity2::class.java).also {
            finish()
        })
    }


}