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
//地理的変数
    private lateinit var inputContainerGeography: LinearLayout
    private lateinit var addButtonGeography: Button
//行動変数
    private lateinit var inputContainerAction: LinearLayout
    private lateinit var addButtonAction: Button

    //onCreate メイン処理
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona_edit_screen)
        //メイン画面へ戻るボタンを取得
        val mainBackBt = findViewById<Button>(R.id.mainBackBt)
        //リスナを作成
        val backListener = MainBackListener()
        //mainBackBtにリスナを入れる。
        mainBackBt.setOnClickListener(backListener)
        
        //地理的変数のedittext追加処理
        inputContainerGeography = findViewById<LinearLayout>(R.id.input_container_geography)
        addButtonGeography = findViewById<Button>(R.id.add_button_geography)
        addButtonGeography.setOnClickListener{
            addInputField(inputContainerGeography)
        }//ボタンにリスナを追加
        //行動変数のEditText追加処理
        inputContainerAction = findViewById<LinearLayout>(R.id.input_container_action)
        addButtonAction = findViewById<Button>(R.id.add_button_action)
        addButtonAction.setOnClickListener{
            addInputField(inputContainerAction)
        }//ボタンにリスナを追加
    }

    //戻るボタンを押したときの処理
    private inner class MainBackListener : View.OnClickListener{
        //前の画面に戻る処理
        override fun onClick(v: View?) {
            finish()
        }

    }

    //EditTextをそれぞれのLinearLayoutに入れるための処理
    private fun addInputField(container: LinearLayout) {
        val newInputField = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hint = "New text"
        }
        container.addView(newInputField)
    }
}