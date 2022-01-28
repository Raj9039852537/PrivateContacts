package ch.abwesend.privatecontacts.domain.model.contact

import ch.abwesend.privatecontacts.domain.Settings
import ch.abwesend.privatecontacts.domain.model.contactdata.ContactData

fun IContactBase.getFullName(
    firstNameFirst: Boolean = Settings.orderByFirstName
): String =
    if (firstNameFirst) "$firstName $lastName"
    else "$lastName $firstName"

fun IContact.asEditable(): ContactEditable =
    if (this is ContactEditable) this
    else toContactEditable(this.contactDataSet.toMutableList())

fun IContactBase.toContactEditable(
    contactDataSet: MutableList<ContactData>
): ContactEditable =
    ContactEditable(
        id = id,
        firstName = firstName,
        lastName = lastName,
        nickname = nickname,
        type = type,
        notes = notes,
        contactDataSet = contactDataSet,
    )
