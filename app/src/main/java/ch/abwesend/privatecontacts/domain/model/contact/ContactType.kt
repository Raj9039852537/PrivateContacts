/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.domain.model.contact

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.ui.graphics.vector.ImageVector
import ch.abwesend.privatecontacts.R

enum class ContactType(@StringRes val label: Int, val icon: ImageVector, val androidPermissionRequired: Boolean) {
    SECRET(label = R.string.secret_contact, icon = Icons.Default.Lock, androidPermissionRequired = false),
    PUBLIC(label = R.string.public_contact, icon = Icons.Default.LockOpen, androidPermissionRequired = true),
}
