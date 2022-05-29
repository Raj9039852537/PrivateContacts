/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.domain.model.contact

import java.util.UUID

interface IContactBase {
    val id: ContactId
    val type: ContactType
    val displayName: String
}

data class ContactBase(
    override val id: ContactId,
    override val type: ContactType,
    override val displayName: String,
) : IContactBase

@JvmInline
value class ContactId(val uuid: UUID) {
    companion object {
        fun randomId(): ContactId = ContactId(UUID.randomUUID())
    }
}

val IContactBase.uuid: UUID
    get() = id.uuid
