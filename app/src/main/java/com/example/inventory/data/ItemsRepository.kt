/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository {
    /**
     * method dari interface untuk mendapatkan semua item dari data source dan bersifat stream.
     */
    fun getAllItemsStream(): Flow<List<Item>>

     /**
     * method dari interface untuk mendapatkan satu item berdasarkan id dari data source dan bersifat stream.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * digunakan untuk menyimpan item ke data source dari parameter item
     */
    suspend fun insertItem(item: Item)

     /**
     * digunakan untuk delete item di data source dari parameter item
     */
    suspend fun deleteItem(item: Item)

    /**
     * digunakan untuk update item di data source dari parameter item
     */
    suspend fun updateItem(item: Item)
}
