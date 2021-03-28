package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon

interface CouponPresenter {
    // Esto se puede traducir como los casos de uso de la app
    // View
    fun showCoupons(coupons: ArrayList<Coupon>?)

    // Interactor
    fun getCoupons()
}