package com.example.finalprojectbatch2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectbatch2.model.Model
import com.example.finalprojectbatch2.databinding.ListbarangBinding
import com.example.finalprojectbatch2.model.Barang

class MainAdapter(private var user : List<Barang>, val listener : MainAdapter.OnClick):RecyclerView.Adapter<MainAdapter.UserViewHolder>() {
    inner class UserViewHolder(val binding : ListbarangBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ListbarangBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            Barang.text = user[position].nama
            Barang.setOnClickListener {
                listener.ListClick(user[position])
            }
            IconEdit.setOnClickListener {
                listener.ImageClick(user[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return user.size
    }
    interface OnClick{
        fun ListClick(post : Barang)
        fun ImageClick(post : Barang)
    }


}