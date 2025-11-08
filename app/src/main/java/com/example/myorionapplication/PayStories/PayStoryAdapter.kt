package com.example.myorionapplication.PayStories

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myorionapplication.R
import com.example.myorionapplication.RateRestoranActivity
import com.example.myorionapplication.module.PayStory
import com.google.android.material.button.MaterialButton

class PayStoryAdapter(
    private val payStoryList: List<PayStory>, private val context: Context
) : RecyclerView.Adapter<PayStoryAdapter.PayStoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PayStoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pay_story_layout, parent, false)
        return PayStoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PayStoryViewHolder, position: Int
    ) {

        val currentNotification = payStoryList[position]
        holder.titleTextPay.text = currentNotification.title
        holder.bonusPay.text = currentNotification.bonus
        holder.imagePay.setImageResource(currentNotification.photo)
        holder.newPay.isVisible = currentNotification.newPay
        holder.datePay.text = currentNotification.data
        holder.minusBonusPay.text = currentNotification.minusBonus
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, RateRestoranActivity::class.java))
            holder.newPay.isVisible = false
        }

    }

    override fun getItemCount(): Int {
        return payStoryList.size
    }

    class PayStoryViewHolder(payStoryView: View) : RecyclerView.ViewHolder(payStoryView) {
        val titleTextPay: TextView = payStoryView.findViewById(R.id.titleTextPayStory)
        val imagePay: ImageView = payStoryView.findViewById(R.id.iconPay)
        val datePay: TextView = payStoryView.findViewById(R.id.datePayStory)
        val newPay: ImageView = payStoryView.findViewById(R.id.newPay)
        val bonusPay: TextView = payStoryView.findViewById(R.id.bonusPay)
        val minusBonusPay: TextView = payStoryView.findViewById(R.id.minusBonusPay)


    }
}
