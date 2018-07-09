package br.com.pdvend.model

import java.io.Serializable

interface IDataGithub: Serializable{
    var number: Int
    var title: String
    var body: String
}