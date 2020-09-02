package com.ekdorn.hogweedgo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapLongClickListener(this)

        // Add a marker in Sydney and move the camera
        val saintPetersburg = LatLng(59.937500, 30.308611)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(saintPetersburg, 10.0F))
    }



    override fun onMapLongClick(p0: LatLng?) {
        if (p0 != null) {
            mMap.addMarker(MarkerOptions().position(p0).title("Hogweed?"))

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Report hogweed!")
            builder.setMessage("Do you want to add extra info about this place?")
            builder.setPositiveButton("Just report") { _: DialogInterface, _: Int -> sendInfo(this, ExtraInfo(p0)) }
            builder.setNegativeButton("Extra info") { _: DialogInterface, _: Int -> sendInfo(this, createExtraInfo(this, p0)) }
            builder.show()
        }
    }
}
