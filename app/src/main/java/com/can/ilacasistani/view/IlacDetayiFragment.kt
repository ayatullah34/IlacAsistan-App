package com.can.ilacasistani.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.can.ilacasistani.R
import com.can.ilacasistani.databinding.FragmentIlacDetayiBinding
import com.can.ilacasistani.util.gorselIndir
import com.can.ilacasistani.util.placeholderYap
import com.can.ilacasistani.viewmodel.IlacDetayiViewModel


class IlacDetayiFragment : Fragment() {

    private lateinit var viewModel: IlacDetayiViewModel
    private var ilacId = 0

    //DATA BINDING
    private lateinit var dataBinding : FragmentIlacDetayiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        val item : MenuItem = menu.findItem(R.id.ilac_add_item)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_ilac_detayi, container, false)

        //DATA BINDING
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_ilac_detayi,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            ilacId = IlacDetayiFragmentArgs.fromBundle(it).ilacId
        }

        viewModel = ViewModelProvider(this)[IlacDetayiViewModel::class.java]
        viewModel.roomVerisiniAl(ilacId)



        observeLiveData(view)

    }

    fun observeLiveData(view: View){
        /*
        val ilacIsim = view.findViewById<TextView>(R.id.ilacIsim)
        val ilacBarkod = view.findViewById<TextView>(R.id.ilacBarkod)
        val ilacFiyat= view.findViewById<TextView>(R.id.ilacFiyat)
        val ilacEtkinMadde = view.findViewById<TextView>(R.id.ilacEtkinMadde)
        val ilacUygulamaSekli = view.findViewById<TextView>(R.id.ilacUygulamaSekli)
        val ilacGorsel= view.findViewById<ImageView>(R.id.ilacImage)
         */

        viewModel.ilacLiveData.observe(viewLifecycleOwner, Observer { ilac->
            ilac?.let{

                dataBinding.secilenIlac = it

            /*
                ilacIsim.text = it.ilacIsim
                ilacBarkod.text = it.ilacBarkod
                ilacFiyat.text = it.ilacFiyat
                ilacEtkinMadde.text = it.ilacEtkinMadde
                ilacUygulamaSekli.text = it.ilacUygulamaSekli
                context?.let {
                    ilacGorsel.gorselIndir(ilac.ilacGorsel, placeholderYap(it))
                }
               */

            }
        })
    }

}