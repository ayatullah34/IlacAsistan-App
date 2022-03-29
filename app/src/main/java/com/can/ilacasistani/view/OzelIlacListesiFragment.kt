package com.can.ilacasistani.view



import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.can.ilacasistani.R
import com.can.ilacasistani.adapter.OzelIlacListesiRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_ozel_ilac_listesi.*

class OzelIlacListesiFragment : Fragment() {

    var ilacIsmiListesi = ArrayList<String>()
    var ilacIdListesi = ArrayList<Int>()
    private lateinit var listeAdapter : OzelIlacListesiRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ozel_ilac_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter = OzelIlacListesiRecyclerAdapter(ilacIsmiListesi,ilacIdListesi)
        ozel_ilac_recyclerView.layoutManager = LinearLayoutManager(context)
        ozel_ilac_recyclerView.adapter = listeAdapter

        sqlVeriAlma()
    }

    fun sqlVeriAlma(){
        try {
            context?.let {
                val database = it.openOrCreateDatabase("Ilaclar",Context.MODE_PRIVATE,null)

                val cursor = database.rawQuery(("SELECT * FROM ilaclar"),null)
                val ilacIsmiIndex = cursor.getColumnIndex("ilacIsmi")
                val ilacIdIndex = cursor.getColumnIndex("id")
                println(ilacIdIndex)

               ilacIsmiListesi.clear()
                ilacIdListesi.clear()

                while (cursor.moveToNext()){
                    ilacIsmiListesi.add(cursor.getString(ilacIsmiIndex))
                    ilacIdListesi.add(cursor.getInt(ilacIdIndex))
                }

                listeAdapter.notifyDataSetChanged()

                cursor.close()
            }



        }
        catch (e:Exception){

        }
    }





}