package br.com.pdvend.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import br.com.pdvend.R
import br.com.pdvend.app.PDVendApplication
import br.com.pdvend.extensions.invisible
import br.com.pdvend.extensions.visible
import br.com.pdvend.ui.adapters.RepositoriesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_repositories.recycler_view_repositories
import kotlinx.android.synthetic.main.fragment_detail.empty_state
import kotlinx.android.synthetic.main.fragment_detail.progress_bar

class ActivityRepositories : PDVendBaseActivity(), RepositoriesAdapter.OnRepositorySelected {

    companion object {
        const val ARG_BUNDLE = "arg_act"
    }

    private val recyclerView: RecyclerView by lazy {
        recycler_view_repositories.layoutManager = LinearLayoutManager(this)
        recycler_view_repositories
    }

    private val viewModel = PDVendApplication.injectViewModel()

    private val progressBar: ProgressBar by lazy {
        progress_bar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        val name = intent.extras.getString(ARG_BUNDLE)
        loadRepository(name)
    }

    private fun loadRepository(name: String){
        subscribe(viewModel.loadRepository(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.items.isEmpty()){
                        empty_state.visible()
                    }else{
                        recyclerView.adapter = RepositoriesAdapter(it.items
                                , this@ActivityRepositories)
                    }
                    progressBar.invisible()
                }, {
                    progressBar.invisible()
                    empty_state.visible()
                }))
    }

    override fun onRepositorySelected(name: String, login: String) {
        val intent = Intent(this, ActivityDataGithub::class.java)
        intent.putExtra(ActivityDataGithub.ARG_NAME, name)
        intent.putExtra(ActivityDataGithub.ARG_LOGIN, login)
        startActivity(intent)
    }

}
