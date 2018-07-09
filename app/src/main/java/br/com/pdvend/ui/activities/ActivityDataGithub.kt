package br.com.pdvend.ui.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import br.com.pdvend.R
import br.com.pdvend.ui.adapters.AdapterDetailIssue
import br.com.pdvend.ui.fragments.FragmentIssueDetailPDVend
import br.com.pdvend.ui.fragments.PDVendBaseFragment
import kotlinx.android.synthetic.main.activity_data_github.detail_tab_layout
import kotlinx.android.synthetic.main.activity_data_github.detail_view_pager

class ActivityDataGithub : PDVendBaseActivity() {

    companion object {
        const val ARG_NAME = "arg_name"
        const val ARG_LOGIN = "arg_login"
    }

    private val viewPager: ViewPager by lazy { detail_view_pager }

    private val tabLayout: TabLayout by lazy { detail_tab_layout }

    private lateinit var fragPullRequest: PDVendBaseFragment

    private lateinit var fragIssues: PDVendBaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_github)

        val name = intent.extras.getString(ARG_NAME)
        val login = intent.extras.getString(ARG_LOGIN)

        fragPullRequest = FragmentIssueDetailPDVend.newInstance(
                isPR = true,
                isIssue = false,
                title = getString(R.string.tab_frag_pull_request)
                ,name = name
                ,login = login
        )

        fragIssues = FragmentIssueDetailPDVend.newInstance(
                isIssue = true,
                isPR = false,
                title = getString(R.string.tab_frag_issues)
                ,name = name
                ,login = login
        )

        val adapter = AdapterDetailIssue(
                supportFragmentManager, listOf(
                fragPullRequest, fragIssues
        )
        )

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
