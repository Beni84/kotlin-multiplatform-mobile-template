package org.example.kotlin.multiplatform.di

import org.example.kotlin.multiplatform.data.NetworkDataSource
import org.example.kotlin.multiplatform.presenter.MainPresenter
import org.example.kotlin.multiplatform.presenter.MainView
import org.example.kotlin.multiplatform.repository.NetworkRepository
import org.example.kotlin.multiplatform.domain.usecase.RetrieveContactsUseCase

class NotDagger {
    private val dataSource = NetworkDataSource()
    private val repository: NetworkRepository by lazy { NetworkRepository(dataSource = dataSource) }

    private fun getUserProfile() = RetrieveContactsUseCase(repository)

    fun helloPresenter(view: MainView): MainPresenter = MainPresenter(view, getUserProfile())
}
