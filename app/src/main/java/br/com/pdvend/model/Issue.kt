package br.com.pdvend.model

data class Issue(
        override var number: Int,
        override var title: String,
        override var body: String) : IDataGithub