package br.com.pdvend.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.pdvend.R
import br.com.pdvend.model.IDataGithub
import kotlinx.android.synthetic.main.item_issue.view.issue_number
import kotlinx.android.synthetic.main.item_issue.view.issue_title

class RecycleViewAdapter(val data: List<IDataGithub>, val listener: OnItemClicked)
    : RecyclerView.Adapter<RecycleViewAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_issue, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int) = data[position]

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private val issueNumber: TextView by lazy { itemView.issue_number }
        private val issueTitle: TextView by lazy { itemView.issue_title }
        private lateinit var listener: OnItemClicked
        private lateinit var data: IDataGithub

        fun bind(data: IDataGithub, listener: OnItemClicked){
            itemView.setOnClickListener(this)
            this.listener = listener
            this.data = data
            issueNumber.text = data.number.toString()
            issueTitle.text = data.title
        }

        override fun onClick(v: View?) {
           listener.onItemClicked(data)
        }
    }

    interface OnItemClicked{
        fun onItemClicked(detail: IDataGithub)
    }
}