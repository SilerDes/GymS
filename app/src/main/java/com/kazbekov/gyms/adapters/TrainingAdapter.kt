package com.kazbekov.gyms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kazbekov.gyms.data.TrainingDay
import com.kazbekov.gyms.databinding.ItemTrainingBinding

class TrainingAdapter(private val onItemClick: (position: Int) -> Unit) :
    ListAdapter<TrainingDay, TrainingAdapter.TrainingViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val itemBinding = ItemTrainingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingViewHolder(itemBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class TrainingViewHolder(itemBinding: ItemTrainingBinding, onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        private val trainingNumber = itemBinding.trainingNumber
        private val duration = itemBinding.duration
        private val trainingScheme = itemBinding.trainingScheme
        private val trainingProcess = itemBinding.trainingProcess

        fun bind(training: TrainingDay) {
            trainingNumber.text = training.trainingNumber.toString()
            trainingScheme.text = training.trainingScheme.name
            duration.text = if (training.endOfTraining != null) {
                // from millis to minutes
                val time = (training.endOfTraining!! - training.startOfTraining) / 60000
                time.toString()
            } else {
                "-"
            }
            trainingProcess.visibility = if (training.isFinished) View.GONE else View.VISIBLE
        }
    }

    class ItemDiffUtilCallback : DiffUtil.ItemCallback<TrainingDay>() {
        override fun areItemsTheSame(oldItem: TrainingDay, newItem: TrainingDay): Boolean {
            return oldItem.trainingNumber == newItem.trainingNumber
        }

        override fun areContentsTheSame(oldItem: TrainingDay, newItem: TrainingDay): Boolean {
            return oldItem == newItem
        }

    }

}