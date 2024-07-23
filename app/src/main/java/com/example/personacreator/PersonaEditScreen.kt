package com.example.personacreator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.reflect.Array.set

//---------------------------------------private変数の宣言↓↓↓
class PersonaEditScreen : AppCompatActivity() {
    //地理的変数のLinearLayoutとBUttonを取得する変数
    private lateinit var inputContainerGeography: LinearLayout
    private lateinit var addButtonGeography: Button
    //行動変数
    private lateinit var inputContainerAction: LinearLayout
    private lateinit var addButtonAction: Button
    //人口動態変数
    private lateinit var inputContainerPopulation: LinearLayout
    private lateinit var addButtonPopulation: Button
    //心理的変数
    private lateinit var inputContainerPsychology: LinearLayout
    private lateinit var addButtonPsychology: Button

    //MainBackBtの取得する用の変数
    private lateinit var mainBackBt : Button
    private lateinit var sharedPreferences : SharedPreferences//データを保存するやつ

    private lateinit var PersonaNameSP : EditText//ペルソナの名前を取得するところ
    private lateinit var PersonaAgeSP : EditText//年齢
    private lateinit var PersonaGenderSP : EditText//性別
    private lateinit var PersonaHeightSP : EditText//身長
    private lateinit var PersonaWeightSP : EditText//体重
    private lateinit var PersonaAffiliationSP : EditText//所属

    //---------------------------------------------onCreate メイン処理
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona_edit_screen)

        //------------------------------------------------------ペルソナで入力のあった情報を取り出して挿入する。
        PersonaNameSP = findViewById<EditText>(R.id.personaName)//ペルソナの名前のIDを取得
        PersonaAgeSP = findViewById<EditText>(R.id.personaAge)
        PersonaGenderSP = findViewById<EditText>(R.id.personaGender)
        PersonaHeightSP = findViewById<EditText>(R.id.personaHeight)
        PersonaWeightSP = findViewById<EditText>(R.id.personaWeight)
        PersonaAffiliationSP = findViewById<EditText>(R.id.personaAffiliation)

        //--------------------------------それぞれの情報をEditTedtに入れている
        sharedPreferences = getSharedPreferences("PersonaInformation", Context.MODE_PRIVATE)//ペルソナ情報を取得するファイルを作成？

        var savedText = sharedPreferences.getString("PersonaName", "")//名前
        PersonaNameSP.setText(savedText)//ペルソナの名前が入っている欄にもう一度入れる。
        savedText = sharedPreferences.getString("PersonaAge", "")//年齢
        PersonaAgeSP.setText(savedText)
        savedText = sharedPreferences.getString("PersonaGender", "")//性別
        PersonaGenderSP.setText(savedText)
        savedText = sharedPreferences.getString("PersonaHeight", "")//身長
        PersonaHeightSP.setText(savedText)
        savedText = sharedPreferences.getString("PersonaWeight", "")//体重
        PersonaWeightSP.setText(savedText)
        savedText = sharedPreferences.getString("PersonaAffiliation", "")//所属
        PersonaAffiliationSP.setText(savedText)

        //--------------------------------メイン画面に戻るための処理の準備　メソッドを入れているところ。
        mainBackBt = findViewById<Button>(R.id.mainBackBt)//メイン画面へ戻るボタンを取得
        //mainBackBtにリスナを入れる
        mainBackBt.setOnClickListener {
            MainActivityBack()
        }

        //---------------------------------ボタンを押したらEditTextが追加される処理の準備　メソッドの挿入
        //地理的変数のedittext追加処理
        inputContainerGeography = findViewById<LinearLayout>(R.id.input_container_geography)
        addButtonGeography = findViewById<Button>(R.id.add_button_geography)
        addButtonGeography.setOnClickListener {
            addInputField(inputContainerGeography)
        }//ボタンにリスナを追加
        //行動変数のEditText追加処理
        inputContainerAction = findViewById<LinearLayout>(R.id.input_container_action)
        addButtonAction = findViewById<Button>(R.id.add_button_action)
        addButtonAction.setOnClickListener {
            addInputField(inputContainerAction)
        }//ボタンにリスナを追加
        //人口動態変数のEditText追加処理
        inputContainerPopulation = findViewById<LinearLayout>(R.id.input_container_population)
        addButtonPopulation = findViewById<Button>(R.id.add_button_population)
        addButtonPopulation.setOnClickListener {
            addInputField(inputContainerPopulation)
        }//ボタンにリスナを追加
        //心理的変数のEditText追加処理
        inputContainerPsychology = findViewById<LinearLayout>(R.id.input_container_psychology)
        addButtonPsychology = findViewById<Button>(R.id.add_button_psychology)
        addButtonPsychology.setOnClickListener {
            addInputField(inputContainerPsychology)
        }//ボタンにリスナを追加
    }

    //------------------------------------------------外部メソッド集合群
    //------------------------------------------------戻るボタンを押したときの処理
    private fun MainActivityBack(){
        //ペルソナの基本情報を保存する
        val editor = sharedPreferences.edit()
            editor.putString("PersonaName",PersonaNameSP.text.toString())
            editor.putString("PersonaAge",PersonaAgeSP.text.toString())
            editor.putString("PersonaGender",PersonaGenderSP.text.toString())
            editor.putString("PersonaHeight",PersonaHeightSP.text.toString())
            editor.putString("PersonaWeight",PersonaWeightSP.text.toString())
            editor.putString("PersonaAffiliation",PersonaAffiliationSP.text.toString())
        editor.apply()
        //前の画面に戻る
        finish()
    }

    //----------------------------EditTextをそれぞれのLinearLayoutに入れるための処理
    private fun addInputField(container: LinearLayout) {
        val editText = createEditText("これが新しいText")
        container.addView(editText)
    }

    //-----------------------------------------追加するEditTextの中身の設定
    private fun createEditText(text:String):EditText {
        return EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hint = "New text"
            setText(text)
        }
    }
}