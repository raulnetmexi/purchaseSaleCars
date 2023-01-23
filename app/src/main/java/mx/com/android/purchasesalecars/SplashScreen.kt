package mx.com.android.purchasesalecars

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        val actionBar = supportActionBar
        actionBar!!.hide()
        val handler = Handler()
        handler.postDelayed({ //Code here
            val myIntent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }, 3000)
        //==================================
    } // OnCreate Method Close Here ========================

}