package com.can.ilacasistani.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.can.ilacasistani.model.Ilac
import com.can.ilacasistani.servis.IlacDatabase
import kotlinx.coroutines.launch
import java.util.*

class IlacDetayiViewModel(application: Application) : BaseViewModel(application) {
    val ilacLiveData = MutableLiveData<Ilac>()

    fun roomVerisiniAl(uuid: Int){
        launch {
            val dao = IlacDatabase(getApplication()).ilacDao()
            val ilac = dao.getIlac(uuid)
            ilacLiveData.value = ilac
        }
    }
}