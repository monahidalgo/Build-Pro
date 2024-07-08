package com.example.buildpro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProjectAdapter(private val projects: MutableList<String>, private val onProjectClick: (String) -> Unit) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val projectNameTextView: TextView = itemView.findViewById(R.id.projectNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_item, parent, false)
        return ProjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val projectName = projects[position]
        holder.projectNameTextView.text = projectName
        holder.itemView.setOnClickListener { onProjectClick(projectName) }
    }

    override fun getItemCount(): Int = projects.size
}