package br.com.pdvend.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.pdvend.R
import br.com.pdvend.extensions.getString
import br.com.pdvend.model.Item
import kotlinx.android.synthetic.main.item_repositories.view.opened_issues
import kotlinx.android.synthetic.main.item_repositories.view.repositorie_name

class RepositoriesAdapter(val data: List<Item>, val listener: OnRepositorySelected)
    : RecyclerView.Adapter<RepositoriesAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_repositories, parent,
                false
        )
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int) = data[position]

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        private val name: TextView by lazy { itemView.repositorie_name }
        private val opnendedIssue: TextView by lazy { itemView.opened_issues }
        private lateinit var listener: OnRepositorySelected
        private lateinit var data: Item

        fun bind(data: Item, listener: OnRepositorySelected) {
            itemView.setOnClickListener(this)
            this.listener = listener
            this.data = data
            name.text = String.format(
                    getString(R.string.repository_name), data.name + "/" + data.owner.login
            )
            opnendedIssue.text = String.format(
                    getString(R.string.open_issue), data
                    .open_issues.toString()
            )
        }

        override fun onClick(v: View?) {
            listener.onRepositorySelected(data.name, data.owner.login)
        }
    }

    interface OnRepositorySelected {
        fun onRepositorySelected(name: String, login: String)
    }
}