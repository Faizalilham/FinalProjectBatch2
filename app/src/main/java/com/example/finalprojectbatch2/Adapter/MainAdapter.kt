package com.example.finalprojectbatch2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectbatch2.model.Model
import com.example.finalprojectbatch2.databinding.ListbarangBinding
import com.example.finalprojectbatch2.model.Barang

class MainAdapter(private var user : MutableList<Barang>, val listener : MainAdapter.OnClick):RecyclerView.Adapter<MainAdapter.UserViewHolder>() {
    inner class UserViewHolder(val binding : ListbarangBinding):RecyclerView.ViewHolder(binding.root)
//    var listfilter = ArrayList<Barang>()

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

//    fun setData(data : ArrayList<Barang>){
//        this.user = data
//        this.listfilter = data
//        notifyDataSetChanged()
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter(){
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//
//                val filterresult = FilterResults()
//                if(constraint == null || constraint.length < 0){
//                    filterresult.count = listfilter.size
//                    filterresult.values = listfilter
//                }else{
//                    val search = constraint.toString().toLowerCase()
//                    var itembarang = ArrayList<Barang>()
//                     for(item in listfilter){
//                         if(item.nama.contains(search)){
//                             itembarang.add(item)
//                         }
//                     }
//                    filterresult.count = itembarang.size
//                    filterresult.values = itembarang
//                }
//                return filterresult
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                user = results!!.values as ArrayList<Barang>
//                notifyDataSetChanged()
//            }
//
//        }
//    }


}