package com.example.healthcare

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context, s: String, nothing: Nothing?, i: Int) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "database.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val qry1 = "CREATE TABLE users (username TEXT, email TEXT, password TEXT)"
        sqLiteDatabase.execSQL(qry1)

        val qry2 = "CREATE TABLE cart (username TEXT, product TEXT, price float,otype TEXT)"
        sqLiteDatabase.execSQL(qry2)

        val qry3 = "CREATE TABLE orderPlace(username TEXT,fullName TEXT,address TEXT, contactNUM TEXT,pinCode TEXT, date TEXT, time TEXT, amount float, otype TEXT)"
        sqLiteDatabase.execSQL(qry3)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Upgrade your database schema here if needed
    }

    fun register(username: String, email: String, password: String) {
        val cv = ContentValues().apply {
            put("username", username)
            put("email", email)
            put("password", password)
        }

        val db: SQLiteDatabase = writableDatabase
        db.insert("users", null, cv)
        db.close()
    }

    fun login(username: String, password: String): Int {
        val db: SQLiteDatabase = readableDatabase
        val selectionArgs = arrayOf(username, password)

        val cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?",selectionArgs)
        val result = if (cursor.moveToFirst()) {
            1 // Match found
        } else {
            0 // No match found
        }

        cursor.close()
        return result
    }

    fun addCart(username: String,product: String,price:Float, otype:String){
        val cv = ContentValues().apply {
            put("username", username)
            put("product", product)
            put("price", price)
            put("otype",otype)
        }

        val db: SQLiteDatabase = writableDatabase
        db.insert("cart", null, cv)
        db.close()
    }

    fun checkCart(username: String, product: String): Int{
        var str = arrayOfNulls<String>(2)
        str[0]=username
        str[1]=product

        val db: SQLiteDatabase = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM cart WHERE username=? AND product=?",str)

        val result = if (cursor.moveToFirst()) {
            1 // Match found
        }else
        {
            0
        }
        db.close()
        return result
    }

    fun removeCart(username:String,otype: String){
        var str=arrayOf("","")
        str[0]=username
        str[1]=otype
        val db: SQLiteDatabase = writableDatabase

        db.delete("cart","username=? and otype=?",str)
        db.close()
    }

    fun getCartData(username: String, otype: String): ArrayList<String>{
        var arr: ArrayList<String> = ArrayList() //empty arrayList
        val db: SQLiteDatabase = readableDatabase // instance of SQLite
        var str = arrayOfNulls<String>(2)   // array empty
        str[0]=username
        str[1]=otype
        val cursor = db.rawQuery("SELECT * FROM cart WHERE username=? AND otype=?",str)

       if (cursor.moveToFirst()) {
           do{
               val product= cursor.getString(1)
               val price=cursor.getString(2)

               arr.add(product+"$"+price)
           }while(cursor.moveToNext())
       }
        db.close()
       return arr
    }

    fun addOrder(
        username: String, fullName: String, address:String, contact:String, pinCode: String,
        date:String,
        time: String,
        price: Float,
        otype: String ){

        val cv = ContentValues().apply {

            put("username", username)
            put("fullName", fullName)
            put("address", address)
            put("contactNum", contact)
            put("pinCode", username)
            put("date", date)
            put("time", time)
            put("amount", price)
            put("otype", otype)
        }
            val db: SQLiteDatabase = writableDatabase// instance of SQLite
            db.insert("orderPlace",null,cv);
            db.close()
    }

    fun getorderData(username: String): ArrayList<String>{
        var arr: ArrayList<String> = ArrayList() // List to store User Orders
        val db: SQLiteDatabase = writableDatabase// instance of SQLite
        var str= arrayOf("")
        str[0]=username
        val c = db.rawQuery("SELECT * FROM orderPlace WHERE username=?",str)

        if (c.moveToFirst()) {
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+
                c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8)+"$"+c.getString(9))
            }while(c.moveToNext())
        }
        db.close()


        return arr
    }


}
