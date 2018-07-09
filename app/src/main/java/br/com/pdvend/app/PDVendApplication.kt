package br.com.pdvend.app

import android.app.Application
import android.content.Context
import br.com.pdvend.repository.GithubRepository
import br.com.pdvend.repository.IGithubRepository
import br.com.pdvend.repository.api.IAPIGithub
import br.com.pdvend.repository.api.RetrofitService
import br.com.pdvend.viewmodel.GithubViewModel
import br.com.pdvend.viewmodel.IGithubViewModel
import java.lang.ref.WeakReference

class PDVendApplication : Application() {

    companion object {
        lateinit var context: WeakReference<Context>
        fun getApplication() = context.get()!!

        private lateinit var githubRepository: IGithubRepository
        private lateinit var api: IAPIGithub
        private lateinit var viewModel: IGithubViewModel

        fun injectViewModel() = viewModel
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(applicationContext)

        api = RetrofitService.get(IAPIGithub::class)
        githubRepository = GithubRepository(api)
        viewModel = GithubViewModel(githubRepository)

    }
}