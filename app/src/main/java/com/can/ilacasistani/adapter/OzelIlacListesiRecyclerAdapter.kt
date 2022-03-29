package com.can.ilacasistani.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.can.ilacasistani.R
import com.can.ilacasistani.view.OzelIlacListesiFragmentDirections
import kotlinx.android.synthetic.main.ozel_ilac_recycler_row.view.*

class OzelIlacListesiRecyclerAdapter(val ilacListesi : ArrayList<String>,val idListesi:ArrayList<Int>) :
    RecyclerView.Adapter<OzelIlacListesiRecyclerAdapter.IlacHolder>() {

    class IlacHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IlacHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ozel_ilac_recycler_row,parent,false)
        return IlacHolder(view)
    }

    override fun onBindViewHolder(holder: IlacHolder, position: Int) {
        holder.itemView.ozel_ilac_recycler_row_text.text = ilacListesi[position]
        holder.itemView.setOnClickListener {
            val action = OzelIlacListesiFragmentDirections.actionOzelIlacListesiFragmentToOzelIlacEkleFragment("recyclerdangeldim",idListesi[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return ilacListesi.size
    }


}