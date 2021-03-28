package com.anncode.offersandcoupons.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
//import com.anncode.offersandcoupons.R
//import com.anncode.offersandcoupons.presenter.CouponPresenter
//import com.anncode.offersandcoupons.view.RecyclerCouponsAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//var couponPresenter: CouponPresenter porque se eliminaron los interactores
class CouponRepositoryImpl(): CouponRepository {

    // Vamos a tener una lista con super poderes, lista provientes de las livedatas son listas reactivas
    // esta lista es la misma que "coupons" que esta en el método callCouponsAPI de abajo, pero tiene
    // la caracteristica de reactiva "live" los cambios refrescan a los demás.
    private var coupons = MutableLiveData<List<Coupon>>()
    // Subject MutableLiveData
    // Contendra una lista de Observers o observadores, es decir nuestra lista de cupones (Obervers List Coupon)
    // Cuando nuestra lista de cupones cambie esto va a afectar el estaod mutable el estado de nuestro sujeto
    // Change List Coupon - MutableLiveDate
    // "observe" -> método de MutableLiveData
    // esta lista tiene super poderes, por ej un metodo "observe" lo que hace es notificar los cambios
    // o actualizar en donde sea que se esta llamando o se este ejecutando ese método especial

    //funcionalidad traer la lista de cupones
    override fun getCoupons(): MutableLiveData<List<Coupon>> {
        return coupons
    }

    //TODA LA LÓGICA DE CONEXIÓN
    override fun callCouponsAPI() {
        TODO("Not yet implemented")

        // CONTROLLER
        var couponsList: ArrayList<Coupon>? = ArrayList<Coupon>() // nuevo
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

                    //se llena una lista de cupones
                    couponsList?.add(coupon) // tenemos que agregar "?" por la definicion de arriba
                }
                // VIEW
                // esto no lo necesitamos porque lo vamos a hacer con nuestro presentador
                //rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)

                //couponPresenter.showCoupons(coupons) porque se elimino los interactores
                // VIEW

                //coupons -> lista con super poderes
                coupons.value = couponsList
            }

        })
    }
}