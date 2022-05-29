/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.infrastructure.room.contact

import ch.abwesend.privatecontacts.domain.model.contact.IContact
import ch.abwesend.privatecontacts.domain.service.FullTextSearchService
import ch.abwesend.privatecontacts.domain.util.getAnywhere

fun IContact.toEntity(): ContactEntity {
    val fullTextSearchColumn = computeFullTextSearchColumn()
    return ContactEntity(
        rawId = id.uuid,
        type = type,
        firstName = firstName,
        lastName = lastName,
        nickname = nickname,
        notes = notes,
        fullTextSearch = fullTextSearchColumn,
    )
}

private fun IContact.computeFullTextSearchColumn(): String {
    val searchService: FullTextSearchService = getAnywhere()
    return searchService.computeFullTextSearchColumn(this)
}
