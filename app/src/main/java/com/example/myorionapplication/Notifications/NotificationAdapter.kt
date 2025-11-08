package com.example.myorionapplication.Notifications


import android.accessibilityservice.GestureDescription
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myorionapplication.R
import com.example.myorionapplication.module.Notification
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.example.myorionapplication.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class NotificationAdapter(
    private val notificationList: List<Notification>,
    private val context: Context
) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notification_card, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotificationViewHolder,
        position: Int
    ) {

        val currentNotification = notificationList[position]
        holder.titleText.text = currentNotification.titleText
        holder.descriptionText.text = currentNotification.text
        holder.imageNotification.setImageResource(currentNotification.photo)
        holder.newNotification.isVisible = currentNotification.newNotification
        holder.dateNotification.text = currentNotification.data

        holder.itemView.setOnClickListener {
            when (currentNotification.titleText) {
                "Дарим 3000 бонусов!!!" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Для того чтобы учавствовать в акции нужно сделать заказ доставки через приложение "
                    )
                    holder.newNotification.isVisible = false

                }

                "Отрытие нового ресторана" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Адрес Усть-Джегута ул.Курортная 217"
                    )
                    holder.newNotification.isVisible = false
                }

                "Пицца в подарок" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Для того, чтобы участвовать в акции нужно сделать заказ на сумму не менее 600 ₽ в заведении Бакари."
                    )
                    holder.newNotification.isVisible = false
                }

                "MMC пиред" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Не отступать и не сдаваться"
                    )
                    holder.newNotification.isVisible = false
                }

                "Роллы по 250Р" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Можете заказать у нас в приложении"
                    )
                    holder.newNotification.isVisible = false
                }

                "Дарим 200 баллов" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Можете заказать у нас в приложении"
                    )
                    holder.newNotification.isVisible = false
                }

                "Дарим 3000 бонусов!" -> {
                    dialogNotification(
                        context,
                        currentNotification.titleText.toString(),
                        currentNotification.text.toString(),
                        "Можете заказать у нас в приложении"
                    )
                    holder.newNotification.isVisible = false
                }


            }
        }

    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    class NotificationViewHolder(notificationView: View) :
        RecyclerView.ViewHolder(notificationView) {
        val titleText: TextView = notificationView.findViewById(R.id.titleTextNotification)
        val descriptionText: TextView =
            notificationView.findViewById(R.id.descriptionTextNotification)
        val imageNotification: ImageView = notificationView.findViewById(R.id.iconNotification)
        val dateNotification: TextView = notificationView.findViewById(R.id.dateNotification)
        val newNotification: ImageView = notificationView.findViewById(R.id.newNotification)


    }

    private fun dialogNotification(
        context: Context,
        title: String,
        description1: String,
        description2: String
    ) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.notification_dialog, null)
        val positiveButton = dialogView.findViewById<MaterialButton>(R.id.positiveButton)
        dialogView.findViewById<TextView>(R.id.titleTextDialogNotification).text = title
        dialogView.findViewById<TextView>(R.id.descrtiptionDialog1).text = description1
        dialogView.findViewById<TextView>(R.id.descrtiptionDialog2).text = description2


        val dialog = AlertDialog.Builder(context).setView(dialogView).create()
        positiveButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
