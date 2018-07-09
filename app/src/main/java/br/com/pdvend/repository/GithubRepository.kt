package br.com.pdvend.repository

import android.util.Log
import br.com.pdvend.model.Issue
import br.com.pdvend.model.PullRequest
import br.com.pdvend.model.Repository
import br.com.pdvend.repository.api.IAPIGithub
import io.reactivex.Observable

class GithubRepository(private val api: IAPIGithub) : IGithubRepository {

    override fun loadIssue(login: String, name: String): Observable<List<Issue>> {
        return loadIssueFromApi(login, name)
    }

    override fun loadPullRequest(login: String, name: String): Observable<List<PullRequest>> {
        Log.d("PDVEND", this.javaClass.simpleName + " loadPullRequest")
        return loadPullRequestFromApi(login, name)
    }

    override fun loadRepository(name: String): Observable<Repository> {
        Log.d("PDVEND", this.javaClass.simpleName + " loadRepository")
        return loadRepositoryFromApi(name)
    }

    private fun loadIssueFromApi(login: String, name: String): Observable<List<Issue>> {
        return api.loadIssues(login, name)
                .doOnNext {
                    Log.d("PDVEND", this.javaClass.simpleName + " loadIssueFromApi")
                }
    }

    private fun loadPullRequestFromApi(login: String, name: String): Observable<List<PullRequest>> {
        return api.loadPullRequests(login, name)
                .doOnNext {
                    Log.d("PDVEND", this.javaClass.simpleName + " loadPullRequestFromApi")
                }
    }

    private fun loadRepositoryFromApi(name: String = ""): Observable<Repository> {
        return api.loadRepositories(name)
                .doOnNext {
                    Log.d("PDVEND", this.javaClass.simpleName + " loadRepositoryFromApi")
                }
    }
}