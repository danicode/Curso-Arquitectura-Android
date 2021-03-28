package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponsInteractor
import com.anncode.offersandcoupons.model.CouponsInteractorImpl
import com.anncode.offersandcoupons.view.CouponView

// orquesta el quilombete
// Aqui el constructor inyeccion de dependencia, inyectamos al contructor una interfaz
class CouponPresenterImpl(var couponView: CouponView): CouponPresenter {

    private var couponInteractor: CouponsInteractor = CouponsInteractorImpl(this)

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        couponView.showCoupons(coupons)
    }

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }
}