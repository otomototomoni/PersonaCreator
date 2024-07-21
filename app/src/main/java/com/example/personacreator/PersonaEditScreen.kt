package com.example.personacreator

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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.Array.set

//---------------------------------------private変数の宣言↓↓↓
class PersonaEditScreen : AppCompatActivity() {
    //地理的変数
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
    //viewModelを取得？
    private val viewModel: PersonaEditViewModel by viewModels()

    private var PersonaNameText = findViewById<TextView>(R.id.personaName).toString()

    //---------------------------------------------onCreate メイン処理
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona_edit_screen)

        val mainBackBt = findViewById<Button>(R.id.mainBackBt)//メイン画面へ戻るボタンを取得
        val backListener = MainBackListener()//リスナ変数を作成
        mainBackBt.setOnClickListener(backListener)//mainBackBtにリスナを入れる
        
        //地理的変数のedittext追加処理
        inputContainerGeography = findViewById<LinearLayout>(R.id.input_container_geography)
        addButtonGeography = findViewById<Button>(R.id.add_button_geography)
        addButtonGeography.setOnClickListener{
            addInputField(inputContainerGeography,viewModel.geographyInputs)
            viewModel.addGeographyInput("")
        }//ボタンにリスナを追加
        //行動変数のEditText追加処理
        inputContainerAction = findViewById<LinearLayout>(R.id.input_container_action)
        addButtonAction = findViewById<Button>(R.id.add_button_action)
        addButtonAction.setOnClickListener{
            addInputField(inputContainerAction,viewModel.actionInputs)
        }//ボタンにリスナを追加
        //人口動態変数のEditText追加処理
        inputContainerPopulation = findViewById<LinearLayout>(R.id.input_container_population)
        addButtonPopulation = findViewById<Button>(R.id.add_button_population)
        addButtonPopulation.setOnClickListener{
            addInputField(inputContainerPopulation,viewModel.populationInputs)
        }//ボタンにリスナを追加
        //心理的変数のEditText追加処理
        inputContainerPsychology = findViewById<LinearLayout>(R.id.input_container_psychology)
        addButtonPsychology = findViewById<Button>(R.id.add_button_psychology)
        addButtonPsychology.setOnClickListener{
            addInputField(inputContainerPsychology,viewModel.psycologyInputs)
        }//ボタンにリスナを追加

        //-------------------------------------------
        //viewmodelインスタンスを作成
        val viewModel: PersonaEditViewModel = ViewModelProvider(this).get(PersonaEditViewModel::class.java)

        // Observe changes in the geography inputs
        viewModel.geographyInputs.observe(this, Observer { inputs ->
            updateInputFields(inputContainerGeography, inputs) { index, text ->
                viewModel.updateGeographyInput(index, text)
            }
        })

        // Observe changes in the action inputs
        viewModel.actionInputs.observe(this, Observer { inputs ->
            updateInputFields(inputContainerAction, inputs) { index, text ->
                viewModel.updateActionInput(index, text)
            }
        })

        // Observe changes in the population inputs
        viewModel.populationInputs.observe(this, Observer { inputs ->
            updateInputFields(inputContainerPopulation, inputs) { index, text ->
                viewModel.updatePopulationInput(index, text)
            }
        })

        // Observe changes in the psychology inputs
        viewModel.psycologyInputs.observe(this, Observer { inputs ->
            updateInputFields(inputContainerPsychology, inputs) { index, text ->
                viewModel.updatePsychologyInput(index, text)
            }
        })

        //---------------------------------------------------observer
        val EditTextObserver = Observer<TextView>{counter ->
            PersonaNameText =counter.toString()
        }
    }




//------------------------------------------------戻るボタンを押したときの処理
    private inner class MainBackListener : View.OnClickListener{
        //前の画面に戻る処理
        override fun onClick(v: View?) {
            viewModel.personaName.value = PersonaNameText
            finish()
        }

    }

    //----------------------------EditTextをそれぞれのLinearLayoutに入れるための処理
    private fun addInputField(container: LinearLayout, inputs: LiveData<MutableList<String>>) {
        val editText = createEditText("")
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
    //----------------------------------------------------
    private fun updateInputFields(container: LinearLayout, inputs: MutableList<String>, onTextChanged: (Int, String) -> Unit) {
        container.removeAllViews()
        inputs.forEachIndexed { index, input ->
            val editText = createEditText(input)
            editText.setText(input)
            container.addView(editText)
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    onTextChanged(index, s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
}