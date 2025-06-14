package com.danicc.fitdis.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.R;

import java.util.ArrayList;
import java.util.List;
/**
 *  Adapter para mostrar cada item de ejercicio en la lista
 *  y sus clicks
 * */
public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exerciseList = new ArrayList<>();
    private OnItemClickListener listener;
    public ExerciseAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setExerciseList(List<Exercise> exercises) {
        this.exerciseList = exercises;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.bind(exercise, listener);

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public interface OnItemClickListener {
        void onEdit(Exercise exercise);

        void onDelete(Exercise exercise);
    }

    static class ExerciseViewHolder extends ViewHolder {
        TextView tvExerciseName, tvMuscleGroup, tvDescription;
        ImageButton editButton, deleteButton;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExerciseName = itemView.findViewById(R.id.tvExerciseName);
            tvMuscleGroup = itemView.findViewById(R.id.tvMuscleGroup);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            editButton = itemView.findViewById(R.id.btnEdit);
            deleteButton = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(Exercise exercise, OnItemClickListener listener) {
            tvExerciseName.setText(exercise.getName());
            tvMuscleGroup.setText(exercise.getMuscleGroup());
            tvDescription.setText(exercise.getDescription());
            editButton.setOnClickListener(v -> listener.onEdit(exercise));
            deleteButton.setOnClickListener(v -> listener.onDelete(exercise));
        }
    }
}
