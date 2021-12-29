package ch.abwesend.privatecontacts.infrastructure.room.contact

import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.abwesend.privatecontacts.domain.model.ContactBase
import ch.abwesend.privatecontacts.domain.model.ContactType
import java.util.UUID

@Entity
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    override val uuid: UUID,
    override val firstName: String,
    override val lastName: String,
    override val nickname: String,
    override val type: ContactType,
) : ContactBase

fun ContactBase.toEntity() = ContactEntity(
    id = id,
    uuid = uuid,
    firstName = firstName,
    lastName = lastName,
    nickname = nickname,
    type = type,
)
