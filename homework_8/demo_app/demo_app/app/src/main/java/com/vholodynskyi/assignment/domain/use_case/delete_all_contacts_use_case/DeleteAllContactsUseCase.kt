package com.vholodynskyi.assignment.domain.use_case.delete_all_contacts_use_case

import com.vholodynskyi.assignment.common.Resource
import com.vholodynskyi.assignment.data.remote.api.contacts.ApiContact
import com.vholodynskyi.assignment.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteAllContactsUseCase @Inject constructor(private val repository: ContactRepository) {

    operator fun invoke() = flow {
        try {
            emit(Resource.Loading<List<ApiContact>>())
            val result = repository.deleteContacts()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server"))
        }
    }
}