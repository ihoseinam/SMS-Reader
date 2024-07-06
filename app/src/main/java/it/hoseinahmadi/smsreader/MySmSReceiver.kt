package it.hoseinahmadi.smsreader

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast

class MySmSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
       /* //Deprecated (Holo sen)
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras as Bundle
            val format = bundle.getString("format")
            //PDus =Protocol Date Unit (S)
            val pdus = bundle.get("pdus") as Array<*>?
            if (pdus != null) {
                var smsMessage = ""
                val messages = mutableListOf<SmsMessage>()
                pdus.forEachIndexed { index, any ->
                    messages.add(SmsMessage.createFromPdu(any as ByteArray, format))
                    smsMessage += "Sms From ${messages[index].originatingAddress} :" +
                            "${messages[index].messageBody}\n"
                }
                Toast.makeText(context, smsMessage, Toast.LENGTH_SHORT).show()
            }
        }*/

        //Ai
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras
            if (bundle != null) {
                val format = bundle.getString("format")
                val pdus = bundle.getSerializable("pdus") as? Array<*>
                if (pdus != null) {
                    var smsMessage = ""
                    val messages = mutableListOf<SmsMessage>()
                    pdus.forEachIndexed { index, any ->
                        val message = SmsMessage.createFromPdu(any as ByteArray, format)
                        messages.add(message)
                        smsMessage += "Sms From ${message.originatingAddress}: ${message.messageBody}\n"
                    }
                    Toast.makeText(context, smsMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}