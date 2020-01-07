package org.example.kotlin.multiplatform.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.example.kotlin.multiplatform.coroutines.getCustomMainScope
import org.example.kotlin.multiplatform.entities.Contact
import org.example.kotlin.multiplatform.domain.usecase.RetrieveContactsUseCase

class MainPresenter(
    private val view: MainView,
    private val retrieveContactsUseCase: RetrieveContactsUseCase,
    private val mainScope: CoroutineScope = getCustomMainScope()
) : CoroutineScope by mainScope {

    private lateinit var _state: ProfileViewState
    private var state: ProfileViewState
        get() = _state
        set(value) = when (value) {
            is ProfileViewState.Loading -> view.showLoading(true)
            is ProfileViewState.Content -> view.showContacts(value.contactList)
            is ProfileViewState.Error -> view.showError("Error: ${value.throwable.message}")
        }

    fun loadContacts() {
        state = ProfileViewState.Loading
        launch {
            @Suppress("TooGenericExceptionCaught")
            state = try {
                val contactList = retrieveContactsUseCase.execute(numContacts)
                view.showLoading(false)
                ProfileViewState.Content(contactList)
            } catch (error: Throwable) {
                view.showLoading(false)
                ProfileViewState.Error(error)
            }
        }
    }

    fun destroy() = cancel()
}

expect val numContacts: Int

interface MainView {
    fun showContacts(contactList: List<Contact>)
    fun showLoading(visible: Boolean)
    fun showError(error: String)
}

sealed class ProfileViewState {
    object Loading : ProfileViewState()
    data class Content(val contactList: List<Contact>) : ProfileViewState()
    data class Error(val throwable: Throwable) : ProfileViewState()
}
