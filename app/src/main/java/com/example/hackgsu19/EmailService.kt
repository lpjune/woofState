package com.example.hackgsu19

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat

class EmailService{
    companion object{
        fun composeEmail(context: Context, addresses: Array<String>, subject: String) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }

            ContextCompat.startActivity(context, intent, Bundle())
        }
    }
}