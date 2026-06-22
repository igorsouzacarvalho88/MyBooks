package com.montyblank.mybooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.montyblank.mybooks.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {

    abstract fun bookDAO(): BookDAO

    companion object{

        private lateinit var instance: BookDatabase
        private const val DATABASE_NAME = "books_database"

        fun getDatabase (context: Context): BookDatabase{
          if (!::instance.isInitialized){
              synchronized(this){
                  instance = Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME)
                      .addMigrations(Migrations.migrationFromV1ToV2)
                      .allowMainThreadQueries()
                      .build()
              }
            }
            return instance
        }
    }

    private object Migrations{
        val migrationFromV1ToV2: Migration = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                if (db.version == 1){
                    db.execSQL("DELETE FROM books")
                }
            }
        }
    }
}
