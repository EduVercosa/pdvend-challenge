package br.com.pdvend.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import br.com.pdvend.R
import kotlinx.android.synthetic.main.activity_main.btn_search
import kotlinx.android.synthetic.main.activity_main.field_search

class MainActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search.isEnabled = false
        field_search.addTextChangedListener(this)
        btn_search.setOnClickListener {
            val intent = Intent(this, ActivityRepositories::class.java)
            intent.putExtra(ActivityRepositories.ARG_BUNDLE, field_search.text.toString())
            startActivity(intent)
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        btn_search.isEnabled = s?.toString()?.length!! > 2
    }
}
