package com.can.ilacasistani.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.can.ilacasistani.R
import kotlinx.android.synthetic.main.fragment_ozel_ilac_ekle.*
import java.lang.Exception
import kotlinx.android.synthetic.main.fragment_ozel_ilac_ekle.btn_guncelle
import kotlinx.android.synthetic.main.fragment_ozel_ilac_ekle.button
import kotlinx.android.synthetic.main.fragment_ozel_ilac_ekle.btn_sil


open class OzelIlacEkleFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_ozel_ilac_ekle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            kaydet(it)
        }

        btn_guncelle.setOnClickListener {
            guncelle(it)
        }
        val buttonSil = view.findViewById<Button>(R.id.btn_sil)
        buttonSil.setOnClickListener {
            sil(it)
        }


        arguments?.let {
            val gelenBilgi = OzelIlacEkleFragmentArgs.fromBundle(it).bilgi
            if(gelenBilgi.equals("menudengeldim")){

                //yeni yemek ekleme
                ilacIsmiText.setText("")
                ilacKullanmaSebebiText.setText("")
                ilacTarihText.setText("")
                ilacKullanmaSikligiText.setText("")
                ilacNotText.setText("")
                button.visibility = View.VISIBLE
                btn_guncelle.visibility = View.INVISIBLE
                btn_sil.visibility = View.INVISIBLE
            }
            else{
                //eski yemekleri listele
                button.visibility = View.INVISIBLE

                val secilenId = OzelIlacEkleFragmentArgs.fromBundle(it).id

                context?.let{
                    try {
                        val db = it.openOrCreateDatabase("Ilaclar",Context.MODE_PRIVATE,null)
                        val cursor = db.rawQuery("SELECT * FROM ilaclar WHERE id = ?", arrayOf(secilenId.toString()))

                        val ilacIsmiIndex = cursor.getColumnIndex("ilacIsmi")
                        val ilacKullanmaSebebiIndex = cursor.getColumnIndex("ilacKullanmaSebebi")
                        val ilacTarihIndex = cursor.getColumnIndex("ilacTarih")
                        val ilacKullanmaSikligiIndex = cursor.getColumnIndex("ilacKullanmaSikligi")
                        val ilacNotIndex = cursor.getColumnIndex("ilacNot")

                        while (cursor.moveToNext()){
                            ilacIsmiText.setText(cursor.getString(ilacIsmiIndex))
                            ilacKullanmaSebebiText.setText(cursor.getString(ilacKullanmaSebebiIndex))
                            ilacTarihText.setText(cursor.getString(ilacTarihIndex))
                            ilacKullanmaSikligiText.setText(cursor.getString(ilacKullanmaSikligiIndex))
                            ilacNotText.setText(cursor.getString(ilacNotIndex))
                        }

                        cursor.close()
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

     fun kaydet(view: View){

        val ilacIsmiText = ilacIsmiText.text.toString()
        val ilacKullanmaSebebiText = ilacKullanmaSebebiText.text.toString()
        val ilacTarihText = ilacTarihText.text.toString()
        val ilacKullanmaSikligiText = ilacKullanmaSikligiText.text.toString()
        val ilacNotText = ilacNotText.text.toString()

        try {
            context?.let{
                val database = it.openOrCreateDatabase("Ilaclar", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS ilaclar (id INTEGER PRIMARY KEY,ilacIsmi VARCHAR,ilacKullanmaSebebi VARCHAR,ilacTarih VARCHAR,ilacKullanmaSikligi VARCHAR,ilacNot VARCHAR)")

                val sqlString = "INSERT INTO ilaclar (ilacIsmi,ilacKullanmaSebebi,ilacTarih,ilacKullanmaSikligi,ilacNot) VALUES(?,?,?,?,?)"
                val statement = database.compileStatement(sqlString)

                if (ilacIsmiText != ""){
                    statement.bindString(1, ilacIsmiText)
                    statement.bindString(2, ilacKullanmaSebebiText)
                    statement.bindString(3, ilacTarihText)
                    statement.bindString(4, ilacKullanmaSikligiText)
                    statement.bindString(5, ilacNotText)
                    statement.execute()
                }

                else{
                    Toast.makeText(context,"Not eklenemedi ilac adı kısmı boş bırakılamaz!!",Toast.LENGTH_LONG).show()
                    //Toast.makeText(context,"Not eklenemedi parametrelerin hepsi boş bırakılamaz!!",Toast.LENGTH_LONG).show()
                }

            }

        }catch (e:Exception){
            e.printStackTrace()
        }

        val action = OzelIlacEkleFragmentDirections.actionOzelIlacEkleFragmentToOzelIlacListesiFragment()
        Navigation.findNavController(view).navigate(action)

    }

    private fun guncelle(view:View){

        val alinanilacIsmi = ilacIsmiText.text.toString()
        val alinanilacKullanmaSebebi = ilacKullanmaSebebiText.text.toString()
        val alinanilacTarih = ilacTarihText.text.toString()
        val alinanilacKullanmaSikligi = ilacKullanmaSikligiText.text.toString()
        val alinanilacNot = ilacNotText.text.toString()

        arguments?.let {

            val secilenId = OzelIlacEkleFragmentArgs.fromBundle(it).id
            println(secilenId)

            context?.let {
                try {
                    val db = it.openOrCreateDatabase("Ilaclar", Context.MODE_PRIVATE, null)
                    if(alinanilacIsmi != "" ){
                        db.execSQL("UPDATE ilaclar SET ilacIsmi = '$alinanilacIsmi'," +
                                "ilacKullanmaSebebi= '$alinanilacKullanmaSebebi'," +
                                "ilacTarih = '$alinanilacTarih'," +
                                "ilacKullanmaSikligi= '$alinanilacKullanmaSikligi'," +
                                "ilacNot = '$alinanilacNot'  WHERE id = ?", arrayOf(secilenId))
                    }
                    else{
                        Toast.makeText(context,"Not güncellenemedi ilac adı kısmı boş bırakılamaz!!",Toast.LENGTH_LONG).show()
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        val action = OzelIlacEkleFragmentDirections.actionOzelIlacEkleFragmentToOzelIlacListesiFragment()
        Navigation.findNavController(view).navigate(action)
    }

    private fun sil(view: View){

        arguments?.let {

            val secilenId = OzelIlacEkleFragmentArgs.fromBundle(it).id
            println(secilenId)

            context?.let {
                try {
                    val db = it.openOrCreateDatabase("Ilaclar", Context.MODE_PRIVATE, null)
                    db.execSQL("DELETE FROM ilaclar WHERE id = ?", arrayOf(secilenId))
                    println("dssdsd")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        val action = OzelIlacEkleFragmentDirections.actionOzelIlacEkleFragmentToOzelIlacListesiFragment()
        Navigation.findNavController(view).navigate(action)

    }


}