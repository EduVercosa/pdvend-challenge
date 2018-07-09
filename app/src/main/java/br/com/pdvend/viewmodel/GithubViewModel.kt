package br.com.pdvend.viewmodel

import android.util.Log
import br.com.pdvend.model.Issue
import br.com.pdvend.model.PullRequest
import br.com.pdvend.model.Repository
import br.com.pdvend.repository.IGithubRepository
import io.reactivex.Observable

class GithubViewModel(private val repository: IGithubRepository) :
        IGithubViewModel {

    override fun loadRepository(name: String): Observable<Repository> {
        return repository.loadRepository(name)
                .map {
                    Log.d("PDVEND", this.javaClass.simpleName + " map - loadRepository")
                    it
                }
                .onErrorReturn {
                    Log.d("PDVEND", this.javaClass.simpleName + " error - loadRepository")
                    Repository(emptyList())
                }
    }

    override fun loadIssues(name: String, login: String): Observable<List<Issue>> {
        return repository.loadIssue(name, login)
                .map { list ->
                    Log.d("PDVEND", this.javaClass.simpleName + " map - loadIssues")
                    list.sortedBy { it.number }
                }.onErrorReturn {
                    Log.d("PDVEND", this.javaClass.simpleName + " error - loadIssues")
                    emptyList()
                }
    }

    override fun loadPullRequests(name: String, login: String): Observable<List<PullRequest>> {
        return repository.loadPullRequest(name, login)
                .map { list ->
                    Log.d("PDVEND", this.javaClass.simpleName + " map - loadPullRequests")
                    list.sortedBy { it.number }
                }
                .onErrorReturn {
                    Log.d("PDVEND", this.javaClass.simpleName + " error - loadPullRequests")
                    emptyList()
                }
    }

}