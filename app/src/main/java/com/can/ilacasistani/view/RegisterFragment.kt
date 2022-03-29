package com.can.ilacasistani.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.can.ilacasistani.R
import kotlinx.android.synthetic.main.fragment_giris_ekrani.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    var kaydedilenKullaniciAdi : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("com.can.ilacasistani",
            Context.MODE_PRIVATE)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    fun kaydet(view: View){
        val kaydetKullaniciAdi = kaydetKullaniciAdi.text.toString()
        val kaydetKullaniciParola = kaydetParola.text.toString()

            if (kaydetKullaniciAdi == "" && kaydetKullaniciParola == ""){
                Toast.makeText(view.context,"Lütfen bir kullanıcı adı veya parola giriniz", Toast.LENGTH_LONG).show()
            }
            else{
                sharedPreferences.edit().putString("kullanici",kaydetKullaniciAdi).apply()
                sharedPreferences.edit().putString("parola",kaydetKullaniciParola).apply()
                val action = RegisterFragmentDirections.actionRegisterFragmentToGirisEkraniFragment2()
                Navigation.findNavController(view).navigate(action)
            }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnKaydet.setOnClickListener {
            kaydet(it)
        }

        btnGiriseDön.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToGirisEkraniFragment2()
            Navigation.findNavController(it).navigate(action)
        }
    }
}