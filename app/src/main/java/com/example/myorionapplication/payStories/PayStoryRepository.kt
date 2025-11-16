package com.example.myorionapplication.payStories

import com.example.myorionapplication.R
import com.example.myorionapplication.module.PayStory

object PayStoryRepository{
    fun getRepository(): List<PayStory>{
       return listOf(
           PayStory(
               title = "Бакари 800р",
               data = "10 апреля",
               bonus = "+120",
               minusBonus = "-710",
               photo = R.drawable.bakari,
               newPay = true
           ), PayStory(
               title = "Docle Salato 4000р",
               data = "21 апреля",
               bonus = "+310",
               minusBonus = "-0",
               photo = R.drawable.dolce_salato,
               newPay = true
           ), PayStory(
               title = "Пиросмани 1200р",
               data = "10 апреля",
               bonus = "0",
               minusBonus = "-110",
               photo = R.drawable.pirosmani,
               newPay = true
           ), PayStory(
               title = "Бакари 800р",
               data = "10 апреля",
               bonus = "+120",
               minusBonus = "-710",
               photo = R.drawable.bakari,
               newPay = true
           ), PayStory(
               title = "Docle Salato 4000р",
               data = "21 апреля",
               bonus = "+310",
               minusBonus = "-0",
               photo = R.drawable.dolce_salato,
               newPay = true
           ), PayStory(
               title = "Пиросмани 1200р",
               data = "10 апреля",
               bonus = "0",
               minusBonus = "-110",
               photo = R.drawable.pirosmani,
               newPay = true
           )
       )
   }
}