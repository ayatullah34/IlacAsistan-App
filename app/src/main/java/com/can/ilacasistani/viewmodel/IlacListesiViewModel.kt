package com.can.ilacasistani.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.can.ilacasistani.model.Ilac
import com.can.ilacasistani.servis.IlacAPIServis
import com.can.ilacasistani.servis.IlacDatabase
import com.can.ilacasistani.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class IlacListesiViewModel(application: Application) : BaseViewModel(application) {
    val ilaclar = MutableLiveData<List<Ilac>>()
    val ilacHataMesaji = MutableLiveData<Boolean>()
    val ilacYukleniyor = MutableLiveData<Boolean>()

    private val ilacApiServis = IlacAPIServis()
    private val disposable = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())
    private var guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData(){

        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani){
            //Sqlite
            verileriSQLitetanAl()
        }
        else{
            verileriInternettenAl()
        }

    }

    fun refreshFromInternet(){
        verileriInternettenAl()
    }

    private fun verileriSQLitetanAl(){

        ilacYukleniyor.value = true

        launch {

            val ilacListesi = IlacDatabase(getApplication()).ilacDao().getAllIlac()
            ilaclariGoster(ilacListesi)
            Toast.makeText(getApplication(),"İlaçları Room'dan Aldık",Toast.LENGTH_LONG).show()
        }
    }

    private fun verileriInternettenAl(){
        ilacYukleniyor.value = true

        disposable.add(
            ilacApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Ilac>>(){
                    override fun onSuccess(t: List<Ilac>) {
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"İlaçları İnternetten Aldık",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        ilacHataMesaji.value = true
                        ilacYukleniyor.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun ilaclariGoster(ilaclarListesi : List<Ilac>){
        ilaclar.value = ilaclarListesi
        ilacHataMesaji.value = false
        ilacYukleniyor.value = false
    }

    private fun sqliteSakla(ilacListesi : List<Ilac>){
        launch {
            val dao = IlacDatabase(getApplication()).ilacDao()
            dao.deleteAllIlac()
            val uuidListesi = dao.insertAll(*ilacListesi.toTypedArray())
            var i = 0
            while (i < ilacListesi.size){
                ilacListesi[i].uuid = uuidListesi[i].toInt()
                i += 1
            }
            ilaclariGoster(ilacListesi)
        }

        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}