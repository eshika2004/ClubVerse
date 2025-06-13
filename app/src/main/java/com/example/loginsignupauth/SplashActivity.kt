package com.example.loginsignupauth

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignupauth.R
import java.util.logging.Handler



class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_LoginSingupAuth)
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_splash)
        android.os.Handler().postDelayed({
            val intent = Intent(/* packageContext = */ this, /* cls = */
                LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}
