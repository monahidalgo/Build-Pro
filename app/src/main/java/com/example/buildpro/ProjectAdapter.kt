package com.example.buildpro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buildpro.R

class ProjectAdapter(
    private val projects: MutableList<Project>,
    private val onClick: (Project) -> Unit,
    private val onDeleteClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view, onClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount(): Int = projects.size

    class ProjectViewHolder(
        itemView: View,
        private val onClick: (Project) -> Unit,
        private val onDeleteClick: (Project) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val projectImageView: ImageView = itemView.findViewById(R.id.projectImageView)
        private val projectNameTextView: TextView = itemView.findViewById(R.id.projectNameTextView)
        private val jobNumberTextView: TextView = itemView.findViewById(R.id.jobNumberTextView)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        fun bind(project: Project) {
            projectImageView.setImageResource(project.imageResId)
            projectNameTextView.text = project.name
            jobNumberTextView.text = project.jobNumber

            itemView.setOnClickListener {
                onClick(project)
            }

            deleteButton.setOnClickListener {
                onDeleteClick(project)
            }
        }
    }
}

class Project {

    var id: Any
        get() {
            TODO("Not yet implemented")
        }
        set(value) {
            TODO("Not yet implemented")
        }
    val jobNumber: CharSequence?
        get() {
            TODO("Not yet implemented")
        }
    val name: CharSequence?
        get() {
            TODO("Not yet implemented")
        }
    val imageResId: Int

    constructor(imageResId: Int, name: String, jobNumber: String) {
        this.imageResId = imageResId
    }
}
