package com.vholodynskyi.assignment.presentation.ui.contactslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vholodynskyi.assignment.common.Resource
import com.vholodynskyi.assignment.data.local.db.contacts.DbContact
import com.vholodynskyi.assignment.domain.use_case.delete_all_contacts_use_case.DeleteAllContactsUseCase
import com.vholodynskyi.assignment.domain.use_case.get_all_contacts_use_case.GetAllContactsUseCase
import com.vholodynskyi.assignment.domain.use_case.insert_all_contacts_use_case.InsertAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsListViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val deleteAllContactsUseCase: DeleteAllContactsUseCase,
    private val insertAllContactsUseCase: InsertAllContactsUseCase
) : ViewModel() {

    private val _contactListState = MutableStateFlow(ContactListState())
    val contactListState : StateFlow<ContactListState> = _contactListState.asStateFlow()

    private fun getAllContacts(){
        viewModelScope.launch {
            getAllContactsUseCase.invoke().collect{ resource->
                when(resource){
                    is Resource.Error -> {
                        _contactListState.update { state -> state.copy(error = resource.message ?: "") }
                    }
                    is Resource.Loading ->{
                        _contactListState.update { state -> state.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _contactListState.update { state -> state.copy(contactList = resource.data ?: emptyList() ) }
                    }
                }
            }
        }
    }

    private fun deleteAllContacts(){
        viewModelScope.launch {
            deleteAllContactsUseCase.invoke().collect{resource ->
                when(resource){
                    is Resource.Error -> {
                        _contactListState.update { state -> state.copy(error = resource.message ?: "") }
                    }
                    is Resource.Loading -> {
                        _contactListState.update { state -> state.copy(isLoading = true) }
                    }
                    else ->{}
                }
            }
        }
    }

    private fun insertAllContacts(list:MutableList<DbContact>){
        viewModelScope.launch {
            insertAllContactsUseCase.invoke(list).collect{resource->
                when(resource){
                    is Resource.Error -> {
                        _contactListState.update { state -> state.copy(error = resource.message ?: "") }
                    }
                    is Resource.Loading ->{
                        _contactListState.update { state -> state.copy(isLoading = true) }
                    }
                    else -> {}
                }
            }
        }
    }

    fun manageEvents(contactEvent: ContactEvent){
        when(contactEvent){
            ContactEvent.DeleteAllContacts -> deleteAllContacts()
            ContactEvent.GetAllContacts -> getAllContacts()
            is ContactEvent.InsertAllContacts -> insertAllContacts(contactEvent.contactList)
        }
    }
}
