package com.can.ilacasistani.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.can.ilacasistani.R
import kotlinx.android.synthetic.main.fragment_ozel_genel_bilgi_ekrani.*


class OzelGenelBilgiEkraniFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        val item: MenuItem = menu.findItem(R.id.ilac_add_item)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ozel_genel_bilgi_ekrani, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genel_ilac_bilgileri_btn.setOnClickListener {
            val actionGenelIlacListesi = OzelGenelBilgiEkraniFragmentDirections.actionOzelGenelBilgiEkraniFragmentToIlacListesiFragment()
            Navigation.findNavController(it).navigate(actionGenelIlacListesi)
        }

        ozel_ilac_bilgiler_btn.setOnClickListener {
            val actionOzelIlacListesi = OzelGenelBilgiEkraniFragmentDirections.actionOzelGenelBilgiEkraniFragmentToOzelIlacListesiFragment()
            Navigation.findNavController(it).navigate(actionOzelIlacListesi)
        }

    }


}