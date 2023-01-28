package com.vholodynskyi.assignment.domain.repository

import com.vholodynskyi.assignment.data.local.db.contacts.DbContact
import com.vholodynskyi.assignment.data.remote.api.contacts.ApiContact

interface ContactRepository {
    suspend fun getContactsList():List<ApiContact>
    suspend fun deleteContacts()
    suspend fun insertContacts(contactList:MutableList<DbContact>)

}