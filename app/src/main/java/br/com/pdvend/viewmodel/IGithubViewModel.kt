package br.com.pdvend.viewmodel

import br.com.pdvend.model.Issue
import br.com.pdvend.model.PullRequest
import br.com.pdvend.model.Repository
import io.reactivex.Observable

interface IGithubViewModel {

    fun loadRepository(name: String) : Observable<Repository>

    fun loadIssues(name: String, login: String) : Observable<List<Issue>>

    fun loadPullRequests(name: String, login: String) : Observable<List<PullRequest>>
}