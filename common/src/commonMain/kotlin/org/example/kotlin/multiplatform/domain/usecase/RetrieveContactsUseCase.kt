package org.example.kotlin.multiplatform.domain.usecase

import org.example.kotlin.multiplatform.entities.Contact
import org.example.kotlin.multiplatform.repository.NetworkRepository

class RetrieveContactsUseCase(
    private val networkRepository: NetworkRepository
) {
    suspend fun execute(numContacts: Int): List<Contact> =
        networkRepository.retrieveUsers(numContacts)
}
