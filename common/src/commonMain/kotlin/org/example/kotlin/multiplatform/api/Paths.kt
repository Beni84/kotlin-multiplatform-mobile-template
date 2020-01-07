package org.example.kotlin.multiplatform.api

object Api {
    private const val path = "/api"

    object Paths {
        const val randomUsers = "?results="
    }

    fun retrieveRandomUsers(numUsers: Int) = "${Api.path}${Paths.randomUsers}$numUsers"
}
