package com.anncode.offersandcoupons.view

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.anncode.offersandcoupons.BR
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.viewmodel.CouponViewModel
import com.squareup.picasso.Picasso

//var coupons : ArrayList<Coupon>?,  -> sacamos del constructor y creamos un atributo coupons y con el metodo setCouponsList lo seteamos en vez que solo sea por conscrucotr
class RecyclerCouponsAdapter(var couponViewModel: CouponViewModel, var resource: Int) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    var coupons: List<Coupon>? = null

    fun setCouponsList(coupons: List<Coupon>?) {
        this.coupons = coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        //var view: View = LayoutInflater.from(p0!!.context).inflate(resource, p0, false)
        //return CardCouponHolder(view)

        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardCouponHolder(binding)
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        //var coupon = coupons?.get(p1)
        //p0.setDataCard(coupon)

        p0.setDataCard(couponViewModel, p1)
    }

    //lo utilizamos cuando estamos pasando los recursos de manera independientes o por otro metodo
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int {
        return resource //nuestro layour nuesto card
    }

    //v: View -> quitamos
    //, View.OnClickListener
    class CardCouponHolder(binding: ViewDataBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        /*private var coupon: Coupon? = null
        private var imgCoupon: ImageView = v.findViewById(R.id.imgCoupon)
        private var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        private var tvDescriptionShort: TextView = v.findViewById(R.id.tvDescriptionShort)
        private var tvCategory: TextView = v.findViewById(R.id.tvCategory)
        private var tvDate: TextView = v.findViewById(R.id.tvDate)*/

        private var binding: ViewDataBinding? = null

        init {
            //v.setOnClickListener(this)
            this.binding = binding
        }

        //coupon: Coupon? -> antes se pasaba como parametro
        fun setDataCard(couponViewModel: CouponViewModel, position: Int){
            /*this.coupon = coupon
            Picasso.get().load(coupon?.image_url).resize(520, 520).centerCrop().into(imgCoupon)
            tvTitle.setText(coupon?.title)
            tvDescriptionShort.setText(coupon?.descriptionShort)
            tvCategory.setText(coupon?.category)
            tvDate.setText(coupon?.endDate)*/

            binding?.setVariable(BR.model, couponViewModel)
            //binding?.setVariable(BR.position, couponViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }

        /*override fun onClick(v: View) {
            Log.i("CLICK Coupon: ", coupon?.title)
            val context = v.context
            val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
            showPhotoIntent.putExtra("COUPON", coupon)
            context.startActivity(showPhotoIntent)

        }*/

    }

}
