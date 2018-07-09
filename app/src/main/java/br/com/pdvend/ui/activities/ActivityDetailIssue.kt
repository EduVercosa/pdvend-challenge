package br.com.pdvend.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.pdvend.R
import br.com.pdvend.model.IDataGithub
import kotlinx.android.synthetic.main.activity_detail_issue.detail_issue_body
import kotlinx.android.synthetic.main.activity_detail_issue.detail_issue_number
import kotlinx.android.synthetic.main.activity_detail_issue.detail_issue_title

class ActivityDetailIssue : AppCompatActivity() {

    companion object {
        const val DETAIL: String = "DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_issue)

        val data = intent?.extras?.get(DETAIL) as IDataGithub?

        data?.let {
            detail_issue_body.text = it.body
            detail_issue_number.text = it.number.toString()
            detail_issue_title.text = it.title
        }
    }

}
