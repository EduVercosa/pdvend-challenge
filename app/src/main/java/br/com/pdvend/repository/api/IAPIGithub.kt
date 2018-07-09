package br.com.pdvend.repository.api

import br.com.pdvend.model.Issue
import br.com.pdvend.model.PullRequest
import br.com.pdvend.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IAPIGithub {

    @GET(RestRoutes.REPOSITORIES_PATH)
    fun loadRepositories(@Query("q") name: String) : Observable<Repository>

    @GET(RestRoutes.ISSUES_PATH)
    fun loadIssues(@Path("login") login: String, @Path("name") name: String) :
            Observable<List<Issue>>

    @GET(RestRoutes.PULL_REQUEST_PATH)
    fun loadPullRequests(@Path("login") login: String, @Path("name") name: String) :
            Observable<List<PullRequest>>
}
