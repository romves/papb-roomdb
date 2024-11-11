package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
// Decorator database disini untuk menandakan bahwa class ini adalah inisialisasi dari room database berisi entity list, version dari migrasi, dan export schema
abstract class InventoryDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null
        /*
          * Decorator Volatile digunakan untuk menandai var instance agar terhindar dari masalah race condition (beberapa thread mencoba membuat instance secara bersamaan).
          * */

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, InventoryDatabase::class.java, "item_database"
                ).build()
                    .also { Instance = it }
            }
        }
        /*
        * disini fungsi getDatabase memastikan bahwa kita membuat instance database Room dan menggunakan ulang di seluruh aplikasi, menghindari pemborosan sumber daya dan memastikan konsistensi data.
        * */
    }



}
