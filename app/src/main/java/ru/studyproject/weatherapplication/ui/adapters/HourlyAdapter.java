package ru.studyproject.weatherapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.studyproject.weatherapplication.R;
import ru.studyproject.weatherapplication.domain.Hourly;

import com.bumptech.glide.Glide;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.viewHolder> {

    private ArrayList<Hourly> localDataSet;
    private Context context;

    public HourlyAdapter(ArrayList<Hourly> dataSet){
        this.localDataSet = dataSet;
    }

    @NonNull
    @Override
    public HourlyAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        this.context = parent.getContext();
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.viewHolder holder, int position) {
        holder.hour.setText(localDataSet.get(position).getHour());
        holder.temp.setText(localDataSet.get(position).getTemp() + "℃");

        int drawableResId = holder.itemView.getResources()
                .getIdentifier(localDataSet.get(position).getPicPath(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResId)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView hour;
        private final TextView temp;
        private final ImageView img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            hour = (TextView) itemView.findViewById(R.id.label_hour_value);
            temp = (TextView) itemView.findViewById(R.id.label_temp_value);
            img = (ImageView) itemView.findViewById(R.id.img_weather_icon);
        }
    }
}
