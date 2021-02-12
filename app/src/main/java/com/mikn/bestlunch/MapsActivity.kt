package com.mikn.bestlunch

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mikn.bestlunch.adapter.CustomInfoWindowAdapter
import com.mikn.bestlunch.model.HotPepperAPIService
import com.mikn.bestlunch.model.Shop
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val UPDATE_INTERVAL = (10 * 1000).toLong()
    private val FASTEST_INTERVAL: Long = 2000

    private var latitude = 0.0
    private var longitude = 0.0
    private var mLocationRequest: LocationRequest? = null
    private var customInfoAdapter: CustomInfoWindowAdapter? = null

    private var requestResult: MutableList<Shop> = mutableListOf()

    private lateinit var mMap: GoogleMap
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        apiRequest.setOnClickListener {
            mMap.clear()
            registerLocationListener()
        }
    }

    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.addMarker(
            MarkerOptions().position(LatLng(latitude, longitude)).title("Current Location")
        )
        customInfoAdapter = CustomInfoWindowAdapter(this@MapsActivity)
        mMap.setInfoWindowAdapter(customInfoAdapter)
    }

    private fun startLocationUpdates() {
        // initialize location request object
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = UPDATE_INTERVAL
            setFastestInterval(FASTEST_INTERVAL)
        }

        // initialize location setting request builder object
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // initialize location service object
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient!!.checkLocationSettings(locationSettingsRequest)

        // call register location listener
        registerLocationListener()
    }

    private fun registerLocationListener() {
        // initialize location callback object
        locationCallback = object : LocationCallback() {
            @SuppressLint("MissingPermission")
            override fun onLocationResult(locationResult: LocationResult?) {
                mMap.isMyLocationEnabled = true
                onLocationChanged(locationResult!!.lastLocation)
            }
        }
        // add permission if android version is greater then 23
        if (Build.VERSION.SDK_INT >= 23 && checkPermission()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, null)
        }
    }

    private fun onLocationChanged(location: Location) {
        // create message for toast with updated latitude and longitude
        val msg = "Updated Location: " + location.latitude + " , " + location.longitude
        // show toast message with updated location
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        val currentLocation = LatLng(location.latitude, location.longitude)
        // mGoogleMap.clear()
        // mGoogleMap.addMarker(MarkerOptions().position(currentLocation).title("Current Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15.0f))
        lifecycleScope.launch(Dispatchers.IO) {
            val response =
                HotPepperAPIService().getRestaurant(location.latitude, location.longitude)
            requestResult.clear()
            response?.apply {
                this.results.shop.forEach {
                    if (it.lat != null && it.lng != null) {
                        val hubeny =
                            Hubeny(location.latitude, location.longitude, it.lat, it.lng)
                        it.distance = hubeny.distance() / 1000
                        requestResult.add(it)
                    }
                }

                withContext(Dispatchers.Main) {
                    requestResult.shuffle()
                    for (index in 0..2) {
                        val it = requestResult[index]
                        mMap.addMarker(MarkerOptions().position(LatLng(it.lat, it.lng)))
                    }
customInfoAdapter?.updateList(requestResult.take(3))
                }
            }
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    private fun checkPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            requestPermissions()
            false
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"),
            1
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION) {
                registerLocationListener()
            }
        }
    }
}
