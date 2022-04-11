/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.infrastructure.calldetection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telecom.CallScreeningService
import android.telephony.PhoneNumberUtils
import android.telephony.TelephonyManager
import ch.abwesend.privatecontacts.domain.Settings
import ch.abwesend.privatecontacts.domain.lib.logging.logger
import ch.abwesend.privatecontacts.domain.model.contact.getFullName
import ch.abwesend.privatecontacts.domain.service.IncomingCallService
import ch.abwesend.privatecontacts.domain.util.applicationScope
import ch.abwesend.privatecontacts.domain.util.injectAnywhere
import kotlinx.coroutines.launch
import java.util.Locale

/**
 * Handle an incoming call via broadcast-receiver.
 * Use this as fallback if using [CallScreeningService] is not an option
 */
class PhoneStateReceiver : BroadcastReceiver() {
    private val incomingCallService: IncomingCallService by injectAnywhere()

    override fun onReceive(context: Context, intent: Intent?) {
        logger.debug("Receiving broadcast")

        if (!Settings.useBroadcastReceiverForIncomingCalls) {
            logger.debug("Broadcast-Receiver is turned off in settings")
            return
        }

        if (intent?.action != TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            // either no intent or an invalid action
            logger.debug("Ignoring invalid broadcast $intent")
            return
        }

        val phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        logger.debug("Received phone state $phoneState")

        if (phoneState == TelephonyManager.EXTRA_STATE_RINGING) {
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            logger.debug("Receiving a call from $incomingNumber")
            incomingNumber?.let { handleIncomingCall(context, it) }
        }
    }

    private fun handleIncomingCall(
        context: Context,
        phoneNumber: String,
    ) {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
        val defaultCountryIso = telephonyManager?.networkCountryIso ?: Locale.getDefault().country.lowercase()

        applicationScope.launch {
            val correspondingContacts = incomingCallService
                .findCorrespondingContacts(phoneNumber, defaultCountryIso)
                .map { it.getFullName() }

            logger.debug("Found corresponding contacts: $correspondingContacts")
            val formattedNumber = PhoneNumberUtils.formatNumber(phoneNumber, defaultCountryIso) ?: phoneNumber
            context.showIncomingCallNotification(formattedNumber, correspondingContacts)
        }
    }
}
