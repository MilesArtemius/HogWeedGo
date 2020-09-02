package com.ekdorn.hogweedgo

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


fun createExtraInfo(app: Activity, latLng: LatLng) : ExtraInfo {
    val info = ExtraInfo(latLng)
    return info
}

fun sendInfo(app: Activity, info: ExtraInfo) {
    val database = Firebase.database
    val myRef = database.getReference("message")

    myRef.child(UUID.randomUUID().toString()).setValue(info)
        .addOnSuccessListener {
            Toast.makeText(app, "Report sent!", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
            Toast.makeText(app, "Report not sent!", Toast.LENGTH_SHORT).show()
        }
}



@IgnoreExtraProperties
class ExtraInfo (val latLng: LatLng) {
    var photoPath: String? = null
    var address: String? = null
    var comment: String? = null
}
