package com.example.accommodationsapp

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.accommodationsapp.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
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
        binding.btnSearch.setOnClickListener {
            val search: String = binding.etSearchField.text.toString()
            val geocoder = Geocoder(this)
            val address = geocoder.getFromLocationName(search, 1)
            val firstAddress = address[0]
            val location = LatLng(firstAddress.latitude, firstAddress.longitude)
            val title = "$search"

            mMap.addMarker(MarkerOptions().position(location).title("Marker in $title"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        }
        
        // Add a marker in Sydney and move the camera
        val seattle = LatLng(47.6, -122.33)
        mMap.addMarker(MarkerOptions().position(seattle).title("Marker in Seattle"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seattle))
    }
}