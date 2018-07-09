package br.com.pdvend.repository.api

object RestRoutes {

    const val REPOSITORIES_PATH: String = "/search/repositories"

    const val ISSUES_PATH: String = "/repos/{login}/{name}/issues?per_page=50"

    const val PULL_REQUEST_PATH: String = "/repos/{login}/{name}/pulls?state=all&per_page=50"
}