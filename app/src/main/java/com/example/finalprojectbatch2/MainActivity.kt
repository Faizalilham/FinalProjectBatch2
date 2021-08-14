package com.example.finalprojectbatch2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.finalprojectbatch2.Adapter.TabNavAdapter
import com.example.finalprojectbatch2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Tabnav()
    }


    override fun onBackPressed() {
        alert()
        finish()
    }

    private fun Tabnav(){
        val FragmentAdapter = TabNavAdapter(supportFragmentManager)
        binding.Pagar.adapter = FragmentAdapter
        binding.Tablayout.setupWithViewPager(binding.Pagar)
    }
    private fun alert(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Warning!")
        alert.setIcon(R.drawable.warning)
        alert.setMessage("Do you want to exit ?")
        alert.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> finish()})
        alert.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        alert.show()
    }

}