package com.danicc.fitdis.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.R;

import java.util.ArrayList;
import java.util.List;
/**
 *  Adapter para mostrar cada item de workout en la lista
 *  y sus clicks
 * */
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<Workout> workouts = new ArrayList<>();
    private final OnWorkoutClickListener listener;

    public interface OnWorkoutClickListener {
        void onWorkoutClick(Workout workout);
    }

    public WorkoutAdapter(OnWorkoutClickListener listener) {
        this.listener = listener;
    }

    public void setWorkouts(List<Workout> list) {
        this.workouts = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        return new WorkoutViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.tvDate.setText("Fecha: " + workout.getDate());
        holder.itemView.setOnClickListener(v -> listener.onWorkoutClick(workout));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvWorkoutDate);
        }
    }
}

