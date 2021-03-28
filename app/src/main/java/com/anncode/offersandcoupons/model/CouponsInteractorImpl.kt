package com.anncode.offersandcoupons.model

import com.anncode.offersandcoupons.presenter.CouponPresenter

class CouponsInteractorImpl(var couponPresenter: CouponPresenter): CouponsInteractor {

    // Estamos utilizando el interactor como un canal para que llegué presentador hasta el repositorio
    // Principio SOLID - Liskov
    private var couponRepository: CouponRepository = CouponRepositoryImpl(couponPresenter)

    override fun getCouponsAPI() {
        couponRepository.getCouponsAPI()
    }
}