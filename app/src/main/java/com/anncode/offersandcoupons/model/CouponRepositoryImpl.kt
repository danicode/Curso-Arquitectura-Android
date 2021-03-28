package com.anncode.offersandcoupons.model

import android.util.Log
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.view.RecyclerCouponsAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {
    //TODA LA LÓGICA DE CONEXIÓN
    override fun getCouponsAPI() {
        TODO("Not yet implemented")

        // CONTROLLER
        var coupons: ArrayList<Coupon>? = ArrayList<Coupon>() // nuevo
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    coupons?.add(coupon) // tenemos que agregar "?" por la definicion de arriba
                }
                // VIEW
                // esto no lo necesitamos porque lo vamos a hacer con nuestro presentador
                //rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)

                couponPresenter.showCoupons(coupons)
                // VIEW
            }

        })

        // Vamos a poder comunicarnos a la interfaz gracias a ese prenseter
        //couponPresenter.showCoupons()
    }
}