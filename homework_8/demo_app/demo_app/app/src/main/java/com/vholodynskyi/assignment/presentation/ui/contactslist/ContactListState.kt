package com.vholodynskyi.assignment.presentation.ui.contactslist

import com.vholodynskyi.assignment.data.remote.api.contacts.ApiContact

data class ContactListState(
    val isLoading: Boolean = false,
    val contactList: List<ApiContact> = emptyList(),
    val error: String = ""
)
