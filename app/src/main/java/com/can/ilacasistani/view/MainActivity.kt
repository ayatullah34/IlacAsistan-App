package com.can.ilacasistani.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.can.ilacasistani.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.ilac_add,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.ilac_add_item){
            val action = OzelIlacListesiFragmentDirections.actionOzelIlacListesiFragmentToOzelIlacEkleFragment()
            Navigation.findNavController(this,R.id.ozel_ilac_recyclerView).navigate(action)
        }

        return super.onOptionsItemSelected(item)
    }
}