package cz.ackee.cleanarch.detekt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.ackee.cleanarch.detekt.features.articles.presentation.ArticlesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ArticlesFragment())
                .commit()
        }
    }
}