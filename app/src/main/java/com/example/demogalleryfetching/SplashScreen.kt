package com.example.demogalleryfetching

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val mainIntent = Intent(this, MainActivity::class.java)
		startActivity(mainIntent)
		finish()
	}
}
