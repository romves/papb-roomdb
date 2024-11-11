package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
/*
* Dao disini digunakan sebagai abstraksi dari akses data ke database.
* */
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    /*
    * Insert digunakan untuk menandai method yang bertujuan untuk memasukkan data ke dalam database. Dengan parameter onConflict strategi Ignore yang berarti mengabaikan data baru jika di database ada primary key yang sama
    * */
    suspend fun insert(item: Item)

    @Update
    /*
    * Menandai metode untuk memperbarui data yang ada di database.
    * Room menentukan data yang diperbarui berdasarkan primary key dalam entitas yang dipassing.
    */
    suspend fun update(item: Item)

    @Delete
    /*
    * Menandai metode untuk menghapus data dari database.
    * Menghapus data yang sesuai dengan primary key item yang diberikan.
    */
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")
    /*
    * Menjalankan query SQL untuk mengambil item berdasarkan id.
    * Mengembalikan Flow<Item> agar data dapat diobservasi secara real-time.
    */
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    /*
    * Menjalankan query SQL untuk mengambil semua item diurutkan berdasarkan kolom name secara ascending.
    * Mengembalikan Flow<List<Item>> untuk observasi data secara real-time.
    */
    fun getAllItems(): Flow<List<Item>>
}