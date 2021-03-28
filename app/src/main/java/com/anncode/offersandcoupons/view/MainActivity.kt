package com.anncode.offersandcoupons.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.R
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.anncode.offersandcoupons.model.ApiAdapter
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.presenter.CouponPresenterImpl

class MainActivity : AppCompatActivity(), CouponView {

    // lo inicializamos en null con el null safely de kotlin con el signo ?
    private var couponPresenter: CouponPresenter? = null
    private var rvCoupons: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        couponPresenter = CouponPresenterImpl(this)

        // START VIEW
        rvCoupons = findViewById(R.id.rvCoupons)

        //debemos poner el signo interrogativo ? porque arriba esta definido como null
        rvCoupons?.layoutManager = LinearLayoutManager(this)

        // Llamada para traer los cupones, es el método implementado más abajo
        // Empieza llamando al presentador
        getCoupons()

        //val coupons = ArrayList<Coupon>()
        // END VIEW

        // START CONTROLLER
        // esto se fue para el modelo a CouponRepository
        /*val apiAdapter = ApiAdapter()
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
                    coupons.add(coupon)
                }
                // VIEW
                rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
                // VIEW
            }

        })*/
        // END CONTROLLER
    }

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        //operador double bang catcheamos una excepcion
        try {
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getCoupons() {
        //empieza llamando al presentador
        couponPresenter?.getCoupons()
    }
}
