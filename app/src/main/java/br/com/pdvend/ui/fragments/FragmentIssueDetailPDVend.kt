package br.com.pdvend.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import br.com.pdvend.R
import br.com.pdvend.app.PDVendApplication
import br.com.pdvend.extensions.getAppContext
import br.com.pdvend.extensions.invisible
import br.com.pdvend.extensions.visible
import br.com.pdvend.model.IDataGithub
import br.com.pdvend.ui.activities.ActivityDetailIssue
import br.com.pdvend.ui.adapters.RecycleViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_detail.detail_recycler_view
import kotlinx.android.synthetic.main.fragment_detail.empty_state
import kotlinx.android.synthetic.main.fragment_detail.progress_bar

class FragmentIssueDetailPDVend : PDVendBaseFragment(), RecycleViewAdapter.OnItemClicked {

    companion object {

        private const val keyFragPullRequest: String = "pullRequest"
        private const val keyFragIssues: String = "issues"
        private const val keyName: String = "keyName"
        private const val keyLogin: String = "keyLogin"
        private const val keyTabTitle: String = "keyTabTitle"

        fun newInstance(isPR: Boolean = false,
                        isIssue: Boolean = false,
                        title: String,
                        name: String,
                        login: String):
                FragmentIssueDetailPDVend {

            val bundle = Bundle()
            bundle.putBoolean(keyFragPullRequest, isPR)
            bundle.putBoolean(keyFragIssues, isIssue)
            bundle.putString(keyName, name)
            bundle.putString(keyLogin, login)
            bundle.putString(keyTabTitle, title)
            val instance = FragmentIssueDetailPDVend()
            instance.arguments = bundle
            return instance
        }
    }

    private val viewModel = PDVendApplication.injectViewModel()

    private val recyclerView: RecyclerView by lazy {
        detail_recycler_view.layoutManager = LinearLayoutManager(getAppContext())
        detail_recycler_view
    }

    private val progressBar: ProgressBar by lazy {
        progress_bar
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pr = arguments?.getBoolean(keyFragPullRequest)
        val issue = arguments?.getBoolean(keyFragIssues)
        val name = arguments?.getString(keyName)
        val login = arguments?.getString(keyLogin)

        pr?.let {
            execute(it, login, name, { login: String, name: String ->
                loadPullRequest(login, name)
            })
        }

        issue?.let {
            execute(it, login, name, { login: String, name: String ->
                loadIssue(login, name)
            })
        }

    }

    private fun execute(param: Boolean, login: String?, name: String?,
                        call: (login: String, name: String) -> Unit) {
        if (param) {
            name?.let { repName ->
                login?.let { repLogin ->
                    call(repLogin, repName)
                }
            }
        }
    }

    private fun loadIssue(login: String, name: String) {
        subscribe(
                viewModel.loadIssues(login, name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showData(it)
                            progressBar.invisible()
                        }, {
                            progressBar.invisible()
                            empty_state.visible()
                        })
        )
    }

    private fun loadPullRequest(login: String, name: String) {
        subscribe(
                viewModel.loadPullRequests(login, name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showData(it)
                            progressBar.invisible()
                        }, {
                            progressBar.invisible()
                            empty_state.visible()
                        })
        )
    }

    private fun showData(data: List<IDataGithub>){
        if(data.isEmpty()){
            empty_state.visible()
        }else{
            recyclerView.adapter = RecycleViewAdapter(
                    data, this@FragmentIssueDetailPDVend
            )
        }
    }

    override fun onItemClicked(detail: IDataGithub) {
        val intent = Intent(activity, ActivityDetailIssue::class.java)
        intent.putExtra(ActivityDetailIssue.DETAIL, detail)
        startActivity(intent)
    }

    override fun fragmentTitle(): String = arguments?.getString(keyTabTitle)!!

    override fun fragmentTag() = javaClass.simpleName

}