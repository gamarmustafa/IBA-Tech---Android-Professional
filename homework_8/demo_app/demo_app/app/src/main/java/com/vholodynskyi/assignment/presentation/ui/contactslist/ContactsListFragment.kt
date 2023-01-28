package com.vholodynskyi.assignment.presentation.ui.contactslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vholodynskyi.assignment.data.local.db.contacts.DbContact
import com.vholodynskyi.assignment.data.remote.api.contacts.ApiContact
import com.vholodynskyi.assignment.databinding.FragmentContactsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
open class ContactsListFragment : Fragment() {

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(
            requireActivity(),
            this::onContactClicked
        )
    }

    private fun onContactClicked(id: Int) {
        findNavController()
            .navigate(ContactsListFragmentDirections.actionContactListToDetails(""))
    }

    private val contactsListViewModel: ContactsListViewModel by viewModels() // delegation
    private var contactList = mutableListOf<DbContact>()
    private var binding: FragmentContactsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllContacts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Creates a vertical Layout Manager
        return FragmentContactsListBinding.inflate(layoutInflater, container, false)
            .apply {
                contactList.layoutManager = LinearLayoutManager(context)
                contactList.adapter = contactAdapter
            }
            .also {
                binding = it
            }
            .root
    }

    private fun insertContacts(appContactList: List<ApiContact>) {
        for (appContact in appContactList) {
            val dbContact = DbContact(
                0,
                appContact.name?.firstName,
                appContact.name?.lastName,
                appContact.email,
                appContact.picture?.thumbnail
            )
            contactList.add(dbContact)
        }
        contactsListViewModel.manageEvents(ContactEvent.InsertAllContacts(contactList))
    }

    fun getAllContacts() {
        contactsListViewModel.manageEvents(ContactEvent.GetAllContacts)
        lifecycleScope.launch {
            contactsListViewModel.contactListState.collect { state ->
                state.contactList.let { list ->
                    contactAdapter.listDiffer.submitList(list)
                    insertContacts(list)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}