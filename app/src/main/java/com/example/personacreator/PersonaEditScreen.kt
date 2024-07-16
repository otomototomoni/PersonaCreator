package com.example.personacreator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PersonaEditScreen : AppCompatActivity() {

    private lateinit var inputContainer: LinearLayout
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona_edit_screen)
        //メイン画面へ戻るボタンを取得
        val mainBackBt = findViewById<Button>(R.id.mainBackBt)
        //リスナを作成
        val backListener = MainBackListener()
        //mainBackBtにリスナを入れる。
        mainBackBt.setOnClickListener(backListener)

        inputContainer = findViewById(R.id.input_container)
        addButton = findViewById(R.id.add_button)

        addButton.setOnClickListener {
            addInputField()
        }
    }

    private inner class MainBackListener : View.OnClickListener{
        //前の画面に戻る処理
        override fun onClick(v: View?) {
            finish()
        }

    }

    private fun addInputField() {
        val newInputField = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hint = "New text"
        }
        inputContainer.addView(newInputField)
    }
}