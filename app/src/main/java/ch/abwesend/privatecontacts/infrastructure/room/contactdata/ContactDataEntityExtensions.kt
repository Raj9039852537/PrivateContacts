/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.infrastructure.room.contactdata

import ch.abwesend.privatecontacts.domain.model.contact.IContactIdInternal
import ch.abwesend.privatecontacts.domain.model.contactdata.ContactData
import ch.abwesend.privatecontacts.domain.model.contactdata.StringBasedContactDataSimple

/**
 * If this produces a build-error "when needs to be exhaustive", although it is exhaustive,
 * just run "clean", then it should work
 */
fun ContactData.toEntity(contactId: IContactIdInternal): ContactDataEntity =
    when (this) {
        is StringBasedContactDataSimple -> stringBasedToEntity(contactId)
    }

private fun StringBasedContactDataSimple.stringBasedToEntity(contactId: IContactIdInternal) =
    ContactDataEntity(
        id = id.uuid,
        contactId = contactId.uuid,
        category = category,
        type = type.toEntity(),
        sortOrder = sortOrder,
        isMain = isMain,
        valueRaw = value,
        valueFormatted = formattedValue,
        valueForMatching = valueForMatching,
    )
