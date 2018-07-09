package br.com.pdvend.repository

import br.com.pdvend.model.Issue
import br.com.pdvend.model.PullRequest
import br.com.pdvend.model.Repository
import io.reactivex.Observable

interface IGithubRepository {

    fun loadIssue(login: String, name: String): Observable<List<Issue>>

    fun loadPullRequest(login: String, name: String): Observable<List<PullRequest>>

    fun loadRepository(name: String): Observable<Repository>

}