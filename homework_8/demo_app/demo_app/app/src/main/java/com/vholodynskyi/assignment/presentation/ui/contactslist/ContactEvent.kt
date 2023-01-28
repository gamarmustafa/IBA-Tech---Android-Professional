package com.vholodynskyi.assignment.presentation.ui.contactslist

import com.vholodynskyi.assignment.data.local.db.contacts.DbContact

sealed class ContactEvent {
    object GetAllContacts : ContactEvent()
    object DeleteAllContacts : ContactEvent()
    data class InsertAllContacts(val contactList:MutableList<DbContact>) : ContactEvent()
}