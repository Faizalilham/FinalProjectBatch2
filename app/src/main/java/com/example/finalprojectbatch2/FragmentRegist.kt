package com.example.finalprojectbatch2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.finalprojectbatch2.API.APIService
import com.example.finalprojectbatch2.API.Constant
import com.example.finalprojectbatch2.model.Model
import com.example.finalprojectbatch2.API.SingleRespon
import com.example.finalprojectbatch2.databinding.FragmentRegistBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentRegist : Fragment() {

    private lateinit var binding : FragmentRegistBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding  = FragmentRegistBinding.inflate(layoutInflater)
        BtnSIgnUp()
        // Inflate the layout for this fragment
        return binding.root

    }


    private fun Regist(){
        val name = binding.ETNameSignUp.text.toString()
        val username = binding.ETUsernameSignUp.text.toString()
        val email = binding.ETEmailSignUp.text.toString()
        val password = binding.ETPasswordSignUp.text.toString()

      if(binding.ETUsernameSignUp.text.isNullOrEmpty() && binding.ETPasswordSignUp.text.isNullOrEmpty() && binding.ETNameSignUp.text.isNullOrEmpty() && binding.ETEmailSignUp.text.isNullOrEmpty()){
          Toast.makeText(activity,"Create Account failed, please fill in all data",Toast.LENGTH_SHORT).show()
      }else{
          APIService.APIEndPoint().SignUp(name, username, email, password).enqueue(object : Callback<SingleRespon<Model>>{
              override fun onResponse(
                      call: Call<SingleRespon<Model>>,
                      response: Response<SingleRespon<Model>>
              ) {
                  if(response.isSuccessful){
                      val body = response.body()
                      if(body != null){
                          Toast.makeText(activity,"Sign Up Success",Toast.LENGTH_SHORT).show()
                          Log.d("Register", "onResponse: ${body.data}")
                      }
                  }
              }

              override fun onFailure(call: Call<SingleRespon<Model>>, t: Throwable) {
                  println(t.message)
              }
          })
      }
    }

    private fun BtnSIgnUp(){
        binding.ButtonSignUp.setOnClickListener {
            Regist()
        }

    }


}