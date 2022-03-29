package com.can.ilacasistani.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.can.ilacasistani.R
import com.can.ilacasistani.databinding.IlacRecyclerRowBinding
import com.can.ilacasistani.model.Ilac
import com.can.ilacasistani.util.gorselIndir
import com.can.ilacasistani.util.placeholderYap
import com.can.ilacasistani.view.IlacListesiFragmentDirections
import kotlinx.android.synthetic.main.ilac_recycler_row.view.*


/*class IlacRecyclerAdapter(private val ilacListesi : ArrayList<Ilac>) : RecyclerView.Adapter<IlacRecyclerAdapter.IlacViewHolder>() {*/

    //DATA BINDING
    class IlacRecyclerAdapter(private val ilacListesi : ArrayList<Ilac>) : RecyclerView.Adapter<IlacRecyclerAdapter.IlacViewHolder>(),IlacClickListener {


        /*class IlacViewHolder(itemView : IlacRe) : RecyclerView.ViewHolder(itemView){}*/

        //DATA BINDING
        class IlacViewHolder(var view : IlacRecyclerRowBinding) : RecyclerView.ViewHolder(view.root){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IlacViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        //val view = inflater.inflate(R.layout.ilac_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<IlacRecyclerRowBinding>(inflater,R.layout.ilac_recycler_row,parent,false)

        return IlacViewHolder(view)

    }

    override fun getItemCount(): Int {
        return ilacListesi.size
    }

    override fun onBindViewHolder(holder: IlacViewHolder, position: Int) {

        //DATA BINDING
        holder.view.ilac = ilacListesi[position]
        holder.view.listener = this

        /*
        val isim = holder.itemView.findViewById<TextView>(R.id.isim)
        isim.text = ilacListesi[position].ilacIsim

        val barkod = holder.itemView.findViewById<TextView>(R.id.barkod)
        barkod.text = ilacListesi[position].ilacBarkod

        holder.itemView.setOnClickListener {
            val action = IlacListesiFragmentDirections.actionIlacListesiFragmentToIlacDetayiFragment(ilacListesi[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        //GÖRSEL
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)
        imageView.gorselIndir(ilacListesi[position].ilacGorsel, placeholderYap(holder.itemView.context))
        */
    }


    @SuppressLint("NotifyDataSetChanged")
    fun ilacListesiniGuncelle(yeniIlacListesi : List<Ilac>){
        ilacListesi.clear()
        ilacListesi.addAll(yeniIlacListesi)
        notifyDataSetChanged()
    }

        //DATA BINDING
        override fun ilacTiklandi(view: View) {
            val uuid = view.ilac_uuid.text.toString().toIntOrNull()
            uuid?.let{
                val action = IlacListesiFragmentDirections.actionIlacListesiFragmentToIlacDetayiFragment(it)
                Navigation.findNavController(view).navigate(action)
            }

        }

}