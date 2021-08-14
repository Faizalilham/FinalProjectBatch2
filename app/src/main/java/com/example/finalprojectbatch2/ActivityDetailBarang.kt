package com.example.finalprojectbatch2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.finalprojectbatch2.API.APIService
import com.example.finalprojectbatch2.API.ListRespon
import com.example.finalprojectbatch2.model.Model
import com.example.finalprojectbatch2.API.SingleRespon
import com.example.finalprojectbatch2.databinding.ActivityDetailBarangBinding
import com.example.finalprojectbatch2.model.Barang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityDetailBarang : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GetbyId()
        BtnDelete()
        ButtonBack()
        val id = intent.getIntExtra("id", 0)
        Log.d("id barang", "$id ")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity2::class.java))
        finish()
    }
    private fun alert(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Warning!")
        alert.setIcon(R.drawable.warning)
        alert.setMessage("are you sure want to delete this item ? ")
        alert.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> DeleteBarang() })
        alert.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        alert.show()
    }

    private fun DeleteBarang(){
        APIService.APIEndPoint().DeleteById(intent.getIntExtra("id",1)).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext,"The item has been deleted",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@ActivityDetailBarang,MainActivity2::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t.message)
            }


        })
    }
    private fun BtnDelete(){
        binding.imgdelete.setOnClickListener {
            alert()

        }
    }

    private fun GetbyId(){
        APIService.APIEndPoint().GetById(intent.getIntExtra("id",0)).enqueue(object : Callback<ListRespon<Barang>> {
            override fun onResponse(call: Call<ListRespon<Barang>>, response: Response<ListRespon<Barang>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ShowDetail(body.data)
                        Log.d("Data by id", "${body.data} ")
                    }
                }
            }

            override fun onFailure(call: Call<ListRespon<Barang>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }
        })

    }




    private fun ShowDetail(post : List<Barang>){
        binding.detailnama.text = post[0].nama
        binding.detailkode.text = post[0].kode.toString()
        binding.detailcreate.text = post[0].createdAt
        binding.detailupdate.text = post[0].updatedAt
    }

    private fun ButtonBack(){
        binding.back.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }

}