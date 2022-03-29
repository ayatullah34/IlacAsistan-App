package com.can.ilacasistani.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.can.ilacasistani.R
import com.can.ilacasistani.adapter.IlacRecyclerAdapter
import com.can.ilacasistani.viewmodel.IlacListesiViewModel
import java.io.File


class IlacListesiFragment : Fragment() {

    private lateinit var viewModel : IlacListesiViewModel
    private  val recyclerIlacAdapter = IlacRecyclerAdapter(arrayListOf())


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
        return inflater.inflate(R.layout.fragment_ilac_listesi, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[IlacListesiViewModel::class.java]
        viewModel.refreshData()

        val ilacListRecycler = view.findViewById<RecyclerView>(R.id.ilacListRecycler)
        ilacListRecycler.layoutManager = LinearLayoutManager(context)
        ilacListRecycler.adapter = recyclerIlacAdapter

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        val ilacHataMesaji = view.findViewById<TextView>(R.id.ilacHataMesaji)
        val ilacYukleniyor= view.findViewById<ProgressBar>(R.id.ilacYukleniyor)

        swipeRefreshLayout.setOnRefreshListener {
            ilacYukleniyor.visibility = View.VISIBLE
            ilacHataMesaji.visibility = View.GONE
            ilacListRecycler.visibility = View.GONE

            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData(view)
    }

    fun observeLiveData(view: View){
        val ilacListRecycler = view.findViewById<RecyclerView>(R.id.ilacListRecycler)
        val ilacHataMesaji = view.findViewById<TextView>(R.id.ilacHataMesaji)
        val ilacYukleniyor= view.findViewById<ProgressBar>(R.id.ilacYukleniyor)

        viewModel.ilaclar.observe(viewLifecycleOwner, Observer { ilaclar->
            ilaclar?.let{
                ilacListRecycler.visibility = View.VISIBLE
                recyclerIlacAdapter.ilacListesiniGuncelle(ilaclar)
            }
        })

        viewModel.ilacHataMesaji.observe(viewLifecycleOwner, Observer {hata->
            hata?.let {
                if(it){
                    ilacHataMesaji.visibility =View.VISIBLE
                    ilacListRecycler.visibility = View.GONE
                }
                else{
                    ilacHataMesaji.visibility = View.GONE
                }
            }
        })

        viewModel.ilacYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let{
                if(it){
                    ilacListRecycler.visibility = View.GONE
                    ilacHataMesaji.visibility = View.GONE
                    ilacYukleniyor.visibility = View.VISIBLE
                }
                else{
                    ilacYukleniyor.visibility = View.GONE
                }
            }
        })
    }


}