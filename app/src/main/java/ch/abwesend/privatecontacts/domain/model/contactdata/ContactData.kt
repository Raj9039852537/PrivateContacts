/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.domain.model.contactdata

import ch.abwesend.privatecontacts.domain.model.ModelStatus
import ch.abwesend.privatecontacts.domain.model.contact.ContactDataId

sealed interface ContactData {
    val id: ContactDataId
    val sortOrder: Int // ascending (0 comes first)
    val category: ContactDataCategory
    val type: ContactDataType
    val isMain: Boolean
    val allowedTypes: List<ContactDataType>
    val isEmpty: Boolean
    val modelStatus: ModelStatus

    val displayValue: String

    fun changeType(type: ContactDataType): ContactData
    fun delete(): ContactData

    /** returning null means that the data will be ignored in search */
    fun formatValueForSearch(): String?
}

interface StringBasedContactData : ContactData {
    /** Raw value */
    val value: String
    /** Formatted for display */
    val formattedValue: String get() = value
    /** Formatted for automatic matching */
    val valueForMatching: String get() = value

    override val isEmpty: Boolean
        get() = value.isEmpty()

    override val displayValue: String
        get() = formattedValue

    override fun formatValueForSearch(): String = formatValueForSearch(value)

    companion object {
        fun formatValueForSearch(value: String): String = value
    }
}

/**
 * The generics are needed to make the functions return the dynamic type of "this"
 */
interface StringBasedContactDataGeneric<T : StringBasedContactDataGeneric<T>> : StringBasedContactData {
    fun changeValue(value: String): T
    override fun changeType(type: ContactDataType): T
    override fun delete(): T
}
