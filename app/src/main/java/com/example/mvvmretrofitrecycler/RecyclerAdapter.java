package com.example.mvvmretrofitrecycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private Context context;
    private List<TaskModel> taskModels = new ArrayList<>();

    public RecyclerAdapter(List<TaskModel> taskModels,Context context) {
        this.taskModels = taskModels;
        this.context = context;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView nameView;
        private TextView idView;
        private TextView ageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.taskName);
            idView = itemView.findViewById(R.id.taskId);
            ageView = itemView.findViewById(R.id.taskAge);

        }
    }

    @NonNull
    @Override
//     возвращает объект ViewHolder, который будет хранить данные по одному объекту
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.task_list_item,parent,false);

        return new RecyclerViewHolder(view);
    }

//выполняет привязку объекта ViewHolder к объекту TaskModel
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {

      if (taskModels.size() != 0){
          TaskModel taskModel = this.taskModels.get(position);
          holder.nameView.setText(taskModel.getName());
          holder.idView.setText(taskModel.getId().toString());
          holder.ageView.setText(taskModel.getAge().toString());
      }



    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public void onChange(List<TaskModel> taskModels ){
        this.taskModels.clear();
        this.taskModels.addAll(taskModels);
        notifyDataSetChanged();
    }

}
