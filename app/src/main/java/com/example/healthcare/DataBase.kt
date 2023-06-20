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

        db.delete("cart","username=? and oytpe=?",str)
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



}
