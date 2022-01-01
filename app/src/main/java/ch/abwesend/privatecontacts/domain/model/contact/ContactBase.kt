package ch.abwesend.privatecontacts.domain.model.contact

import java.util.UUID

interface ContactBase {
    val id: UUID
    val firstName: String
    val lastName: String
    val nickname: String
    val type: ContactType
    val notes: String
}

data class ContactLite(
    override val id: UUID,
    override val firstName: String,
    override val lastName: String,
    override val nickname: String,
    override val type: ContactType,
    override val notes: String,
) : ContactBase
