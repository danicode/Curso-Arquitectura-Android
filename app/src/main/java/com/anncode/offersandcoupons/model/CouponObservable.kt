package com.anncode.offersandcoupons.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class CouponObservable: BaseObservable() {

    private var couponRepository: CouponRepository = CouponRepositoryImpl()

    //Repositorio
    fun callCoupons() {
        couponRepository.callCouponsAPI() //este metodo se encargar de hacer la conexion con el api
    }

    //ViewModel -> conexion directa
    fun getCoupons() : MutableLiveData<List<Coupon>> {
        return couponRepository.getCoupons()
    }
}