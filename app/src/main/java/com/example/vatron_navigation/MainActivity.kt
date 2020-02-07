package com.example.vatron_navigation

import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.util.Log

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.LocationManager
import android.location.LocationListener
import android.location.Location
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest


import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View


class MainActivity : AppCompatActivity() {


    //Our variables in case we want to add imageview
    private var mImageView: ImageView? = null
    private var mUri: Uri? = null
    //Our widgets
    private lateinit var btnCapture: Button
    private lateinit var btnChoose : Button
    //Our constants
    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var locationManager : LocationManager? = null


    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?



        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Camera starting", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            //camera start karne ka intent

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }


//        val fab2: FloatingActionButton = findViewById(R.id.fab2)
//        fab2.setOnClickListener { view ->
//            Snackbar.make(view, "Gallery starting", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//
//            openGallery()
//
//
//        }


        //now its compulsory to initialize button

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*

        val share: Button = findViewById(R.id.share)
        share.setOnClickListener {
            val s = "hello"
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s)
            startActivity(Intent.createChooser(shareIntent,"Share via"))

        }

         */

        //isme initialisation ka kuch jhole hai

       //Android documentation has both codes for sharing the application but neeche wala hi chlaraha hai

    }

    fun sendMessage(view: View) {

//share karne ke liye intent

        val s = "Download the app by Team HMMMM from playstore now" +
                "yahape koibhi link dalsakte hai apan"
        //Intent to share the text
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, s)
        startActivity(Intent.createChooser(shareIntent,"Share via"))


    }

    fun locationbhej(view: View){
        Snackbar.make(view, "sending location", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }

    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            //thetext.text = ("" + location.longitude + ":" + location.latitude)

            //ye do variables me store karva denge
            val longitudeuser=location.longitude
            val latitueuser=location.latitude

        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    fun choosetosend(view: View) {


        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    private fun openGallery(){
        //gallery se image lene ka intent

        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
    }





}

