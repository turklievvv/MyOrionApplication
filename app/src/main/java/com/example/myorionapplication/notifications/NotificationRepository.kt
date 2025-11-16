package com.example.myorionapplication.module

import com.example.myorionapplication.R
import com.example.myorionapplication.module.Notification

object NotificationRepository {

    fun getNotifications(): List<Notification> {
        return listOf(
            Notification(
                titleText = "Дарим 3000 бонусов!!!",
                text = "Дарим 3000 бонусов за ваш первый заказ через доставку",
                photo = R.drawable.orion_svj,
                data = "Сегодня",
                newNotification = true
            ),
            Notification(
                titleText = "Отрытие нового ресторана",
                text = "20 апреля приглашаем всех на открытие нового ресторана в Черкесске",
                photo = R.drawable.info_black,
                data = "20 октября",
                newNotification = true
            ),
            Notification(
                titleText = "Пицца в подарок",
                text = "12,13,14 котября при заказе в Усть-Джегуту на сумму от 600Р пицца бесплатна",
                photo = R.drawable.orion_svj,
                data = "10 октября",
                newNotification = true
            ),
            Notification(
                titleText = "MMC пиред",
                text = "Присоеденяйтесь к нам в MMC",
                photo = R.drawable.android_black,
                data = "5 июня",
                newNotification = true
            ),
            Notification(
                titleText = "Роллы по 250Р",
                text = "Весь октябрь в Dolce Salato роллы по 250Р",
                photo = R.drawable.orion_svj,
                data = "2 октября",
                newNotification = true
            ),
            Notification(
                titleText = "Дарим 200 баллов",
                text = "Дарим 200 баллов на счет при заказе доставки через приложение",
                photo = R.drawable.orion_svj,
                data = "11 сентября",
                newNotification = true
            ),
            Notification(
                titleText = "Дарим 3000 бонусов!",
                text = "Дарим 3000 бонусов за ваш первый заказ через доставку",
                photo = R.drawable.orion_svj,
                data = "Сегодня",
                newNotification = true
            )
        )
    }
}
