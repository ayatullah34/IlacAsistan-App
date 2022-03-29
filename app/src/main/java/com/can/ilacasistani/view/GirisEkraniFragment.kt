package com.can.ilacasistani.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.can.ilacasistani.R
import kotlinx.android.synthetic.main.fragment_giris_ekrani.*
import kotlinx.android.synthetic.main.fragment_giris_ekrani.textView

class GirisEkraniFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    var alinanKullaniciAdi : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("com.can.ilacasistani",Context.MODE_PRIVATE)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item: MenuItem = menu.findItem(R.id.ilac_add_item)
        item.isVisible = false
    }

    fun girisYap(view: View){
        val girilenkullaniciAdi = girisKullaniciAdi.text.toString()
        val kaydedilenKullaniciAdi = sharedPreferences.getString("kullanici","")
        val girilenParola = girisParola.text.toString()
        val kaydedilenParola = sharedPreferences.getString("parola","")

        if (girilenkullaniciAdi == kaydedilenKullaniciAdi && girilenParola == kaydedilenParola){
            val action = GirisEkraniFragmentDirections.actionGirisEkraniFragment2ToOzelGenelBilgiEkraniFragment2()
            Navigation.findNavController(view).navigate(action)
        }
        else{
            Toast.makeText(view.context,"Kullanıcı adı veya parola hatalı",Toast.LENGTH_LONG).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGirisYap.setOnClickListener {
            girisYap(it)
        }

        btnKayitOl.setOnClickListener {
            val action = GirisEkraniFragmentDirections.actionGirisEkraniFragment2ToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        alinanKullaniciAdi = sharedPreferences.getString("kullanici","")

        if(alinanKullaniciAdi != null){
            textView.text = "Sağlıklı günler: $alinanKullaniciAdi"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_giris_ekrani, container, false)
    }




}