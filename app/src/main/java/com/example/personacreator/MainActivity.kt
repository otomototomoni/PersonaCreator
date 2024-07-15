package com.example.personacreator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //メイン画面の新規ペルソナ作成ボタンを取得
        val newCreatePersonaButton = findViewById<Button>(R.id.createBt)
        //リスナを作成
        val listener = NewCreatePersona()
        newCreatePersonaButton.setOnClickListener(listener)

    }

    //新規ペルソナ作成ボタンを押したときのリスナ
    private inner class NewCreatePersona : View.OnClickListener{
        override fun onClick(v: View?) {
            val intentPersonaEditScrean = Intent(this@MainActivity,PersonaEditScreen::class.java)
            startActivity(intentPersonaEditScrean)
        }
    }
}