package com.can.ilacasistani.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Ilac(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val ilacIsim:String?,

    @ColumnInfo(name = "barkod")
    @SerializedName("barkod")
    val ilacBarkod:String?,

    @ColumnInfo(name = "fiyat")
    @SerializedName("fiyat")
    val ilacFiyat:String?,

    @ColumnInfo(name = "etkinMadde")
    @SerializedName("etkinMadde")
    val ilacEtkinMadde:String?,

    @ColumnInfo(name = "uygulamaSekli")
    @SerializedName("uygulamaSekli")
    val ilacUygulamaSekli:String?,

    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val ilacGorsel:String?
) {

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}