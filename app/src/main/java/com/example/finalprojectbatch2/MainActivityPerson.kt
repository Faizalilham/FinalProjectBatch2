package com.example.finalprojectbatch2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.finalprojectbatch2.API.Constant
import com.example.finalprojectbatch2.databinding.ActivityMainPersonBinding

class MainActivityPerson : AppCompatActivity() {
    private lateinit var binding : ActivityMainPersonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GetData()
        Logout()
        back()
    }

    private fun Logout(){
        binding.BtnLogout.setOnClickListener {
            Constant.ClearToken(this)
            Constant.ClearNama(this)
            Constant.ClearUsername(this)
            Constant.ClearEmail(this)
            alert()
        }
    }
    private fun GetData(){
     binding.TvNama.text = "Haii "+Constant.GetNama(this)+" Nice to meet you againt"
     binding.TVUsername.text = "Username : " +Constant.GetUsername(this)
     binding.TVEmail.text = "Email : " +Constant.GetEmail(this)

    }
    private fun back(){
        binding.backtoearly.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java).also {
                finish()
            })
            finish()
        }
    }
    private fun alert(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Warning!")
        alert.setIcon(R.drawable.warning)
        alert.setMessage("Do you want to exit ?")
        alert.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int ->
            startActivity(Intent(this,MainActivity::class.java).also {
                finish()
            })  })
        alert.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        alert.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity2::class.java).also {
            finish()
        })
    }
}