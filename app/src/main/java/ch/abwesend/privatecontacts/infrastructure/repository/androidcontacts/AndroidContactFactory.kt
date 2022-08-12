package ch.abwesend.privatecontacts.infrastructure.repository.androidcontacts

import ch.abwesend.privatecontacts.domain.lib.logging.logger
import ch.abwesend.privatecontacts.domain.model.contact.ContactBase
import ch.abwesend.privatecontacts.domain.model.contact.ContactEditable
import ch.abwesend.privatecontacts.domain.model.contact.ContactIdAndroid
import ch.abwesend.privatecontacts.domain.model.contact.ContactType
import ch.abwesend.privatecontacts.domain.model.contact.IContact
import ch.abwesend.privatecontacts.domain.model.contact.IContactBase
import com.alexstyl.contactstore.Contact

fun Contact.toContactBase(rethrowExceptions: Boolean): IContactBase? =
    try {
        ContactBase(
            id = ContactIdAndroid(contactNo = contactId),
            type = ContactType.PUBLIC,
            displayName = displayName,
        )
    } catch (t: Throwable) {
        logger.warning("Failed to map android contact with id = $contactId", t)
        if (rethrowExceptions) throw t
        else null
    }

fun Contact.toContact(rethrowExceptions: Boolean): IContact? =
    try {
        ContactEditable(
            id = ContactIdAndroid(contactNo = contactId),
            type = ContactType.PUBLIC,
            firstName = firstName,
            lastName = lastName,
            nickname = nickname,
            notes = note?.raw.orEmpty(),
            contactDataSet = getContactData().toMutableList(),
            contactGroups = mutableListOf() // TODO implement
        )
    } catch (t: Throwable) {
        logger.warning("Failed to map android contact with id = $contactId", t)
        if (rethrowExceptions) throw t
        else null
    }
