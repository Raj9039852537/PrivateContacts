/*
 * internal Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.infrastructure.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import ch.abwesend.privatecontacts.domain.settings.SettingsState

internal data class SettingsEntry<T>(val key: Preferences.Key<T>, val defaultValue: T)
internal data class EnumSettingsEntry<T : Enum<T>>(val key: Preferences.Key<String>, val defaultValue: T)

internal val darkThemeEntry = EnumSettingsEntry(
    key = stringPreferencesKey("appTheme"),
    defaultValue = SettingsState.defaultSettings.appTheme
)
internal val orderByFirstNameEntry = SettingsEntry(
    key = booleanPreferencesKey("orderByFirstName"),
    defaultValue = SettingsState.defaultSettings.orderByFirstName
)
internal val showContactTypeInListEntry = SettingsEntry(
    key = booleanPreferencesKey("showContactTypeInList"),
    defaultValue = SettingsState.defaultSettings.showContactTypeInList
)
internal val showExtraButtonsInEditScreenEntry = SettingsEntry(
    key = booleanPreferencesKey("showExtraButtonsInEditScreen"),
    defaultValue = SettingsState.defaultSettings.showExtraButtonsInEditScreen
)
internal val invertTopAndBottomBarsEntry = SettingsEntry(
    key = booleanPreferencesKey("invertTopAndBottomBars"),
    defaultValue = SettingsState.defaultSettings.invertTopAndBottomBars
)
internal val incomingCallsOnLockScreenEntry = SettingsEntry(
    key = booleanPreferencesKey("showIncomingCallsOnLockScreen"),
    defaultValue = SettingsState.defaultSettings.showIncomingCallsOnLockScreen
)
internal val showAndroidContactsEntry = SettingsEntry(
    key = booleanPreferencesKey("showAndroidContacts"),
    defaultValue = SettingsState.defaultSettings.showAndroidContacts
)
internal val initialInfoDialogEntry = SettingsEntry(
    key = booleanPreferencesKey("showInitialInfoDialog"),
    defaultValue = SettingsState.defaultSettings.showInitialAppInfoDialog
)
internal val requestIncomingCallPermissionsEntry = SettingsEntry(
    key = booleanPreferencesKey("requestIncomingCallPermissions"),
    defaultValue = SettingsState.defaultSettings.requestIncomingCallPermissions
)
internal val observeIncomingCallsEntry = SettingsEntry(
    key = booleanPreferencesKey("observeIncomingCalls"),
    defaultValue = SettingsState.defaultSettings.observeIncomingCalls
)
internal val sendErrorsToCrashlyticsEntry = SettingsEntry(
    key = booleanPreferencesKey("sendErrorsToCrashlytics"),
    defaultValue = SettingsState.defaultSettings.sendErrorsToCrashlytics
)
internal val defaultContactTypeEntry = EnumSettingsEntry(
    key = stringPreferencesKey("defaultContactType"),
    defaultValue = SettingsState.defaultSettings.defaultContactType
)
internal val currentVersionEntry = SettingsEntry(
    key = intPreferencesKey("currentVersion"),
    defaultValue = SettingsState.defaultSettings.currentVersion
)
