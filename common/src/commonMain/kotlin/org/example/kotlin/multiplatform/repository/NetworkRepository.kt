package org.example.kotlin.multiplatform.repository

import org.example.kotlin.multiplatform.data.NetworkDataSource
import org.example.kotlin.multiplatform.data.model.toModel
import org.example.kotlin.multiplatform.entities.Contact

class NetworkRepository(
    private val dataSource: NetworkDataSource
) {

    suspend fun retrieveUsers(numContacts: Int): List<Contact> {
        return dataSource.retrieveUsers(numContacts).results.toModel()
    }
}
