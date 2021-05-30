package com.example.finalprojectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.finalprojectbatch2.API.Constant
import com.example.finalprojectbatch2.databinding.ActivityMainPersonBinding

class MainActivityPerson : AppCompatActivity() {
    private lateinit var binding : ActivityMainPersonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Logout()
        GetData()
    }
    private fun Logout(){
        binding.BtnLogout.setOnClickListener {
            Constant.ClearToken(this)
            Constant.ClearNama(this)
            Constant.ClearUsername(this)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    private fun GetData(){
     binding.TvNama.text = "Welcome "+Constant.GetNama(this)
     binding.TVUsername.text = "Username : " +Constant.GetUsername(this)

    }
}