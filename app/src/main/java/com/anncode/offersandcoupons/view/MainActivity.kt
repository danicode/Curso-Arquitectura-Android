package com.anncode.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
/*import android.util.Log*/
//import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.databinding.ActivityMainBinding
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel

/*import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.anncode.offersandcoupons.model.ApiAdapter
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.presenter.CouponPresenterImpl*/

//, CouponView
class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel? = null

    // lo inicializamos en null con el null safely de kotlin con el signo ?
    //private var couponPresenter: CouponPresenter? = null // el código comentado es del MVP
    private var rvCoupons: androidx.recyclerview.widget.RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setUpBindings(savedInstanceState)

        //couponPresenter = CouponPresenterImpl(this)

        // START VIEW
        //rvCoupons = findViewById(R.id.rvCoupons) // no utilizamos más findViewById

        //debemos poner el signo interrogativo ? porque arriba esta definido como null
        //esto no lo vamos a hacer de esta forma
        /*rvCoupons?.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)*/

        //callCoupons
        //getCoupons - Lista de cupones

        // Llamada para traer los cupones, es el método implementado más abajo
        // Empieza llamando al presentador
        //getCoupons()
    }

    //ESTO DA ERROR PORQUE ELIMINAMOS LA INTERFACE couponview
    /* override fun showCoupons(coupons: ArrayList<Coupon>?) {
         //operador double bang catcheamos una excepcion
         try {
             rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
         } catch (e: Exception) {
             e.printStackTrace()
         }

     }

     override fun getCoupons() {
         //empieza llamando al presentador
         //couponPresenter?.getCoupons()
     }*/

    // va a  poner en orbita el sistema de bnidee en accion empiece a funcionar todo lo que esta implementado
    fun setUpBindings(savedInstanceState: Bundle?) {
        //esta clase se crea automaticamente gracias al sistema de bindeo de android
        var activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)

        activityMainBinding.setModel(couponViewModel)
    }

    //va a tener la logica de levantar la lista nuestro recycleview
    fun setupListUpdate() {
        //CallCoupons
        couponViewModel?.callCoupons() //se rellena la lista

        //getCoupons - Lista de cupones
        couponViewModel?.getCoupons()?.observe(this, Observer { coupons: List<Coupon> ->
            Log.w("COUPON", coupons.get(0).title)
            couponViewModel?.setCouponsInRecyclerAdaper(coupons)
        }) //me devuele la lista con supoer poderes
    }
}
