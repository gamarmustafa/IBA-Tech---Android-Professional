package com.vholodynskyi.assignment.data.repository

import com.vholodynskyi.assignment.data.local.db.contacts.ContactsDao
import com.vholodynskyi.assignment.data.local.db.contacts.DbContact
import com.vholodynskyi.assignment.data.remote.api.contacts.ApiContact
import com.vholodynskyi.assignment.data.remote.api.contacts.ContactsService
import com.vholodynskyi.assignment.domain.repository.ContactRepository
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val api: ContactsService,
    private val dao: ContactsDao
): ContactRepository {
    override suspend fun getContactsList(): List<ApiContact> {
        return api.getContacts(30).results!!
    }

    override suspend fun deleteContacts() {
        dao.deleteAll()
    }

    override suspend fun insertContacts(contactList: MutableList<DbContact>) {
        dao.addAll(contactList)
    }
}