package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Yap Wei Jie")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.myName = myName
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonDone.setOnClickListener {
            addNickname(it)
        }
        findViewById<TextView>(R.id.textViewNickname_text).setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View)  {
        binding.apply {
            myName?.nickname = editTextNicknameEdit.text.toString()
            invalidateAll()
            editTextNicknameEdit.visibility = View.GONE
            buttonDone.visibility = View.GONE
            textViewNicknameText.visibility = View.VISIBLE
        }

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    private fun updateNickname (view: View)  {
        val editText = findViewById<EditText>(R.id.editTextNickname_edit)
        val doneButton = findViewById<Button>(R.id.buttonDone)
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        editText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}
