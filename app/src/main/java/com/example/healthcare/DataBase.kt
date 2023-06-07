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



}
