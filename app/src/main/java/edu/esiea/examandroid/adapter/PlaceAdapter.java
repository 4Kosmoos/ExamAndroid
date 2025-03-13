package edu.esiea.examandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.esiea.examandroid.R;
import edu.esiea.examandroid.data.dto.PlaceWithDetails;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    public interface OnPlaceClickListener {
        void onEditClicked(PlaceWithDetails pwd);
        void onDeleteClicked(PlaceWithDetails pwd);
    }

    private List<PlaceWithDetails> data = new ArrayList<>();
    private OnPlaceClickListener listener;

    public PlaceAdapter(OnPlaceClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<PlaceWithDetails> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        PlaceWithDetails pwd = data.get(position);
        holder.bind(pwd, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtType;
        Button btnEdit, btnDelete;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtType = itemView.findViewById(R.id.txtType);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(PlaceWithDetails pwd, OnPlaceClickListener listener) {
            txtName.setText(pwd.getPlace().getName());
            txtType.setText(pwd.getPlace().getType().toString());
            // TODO icône.

            btnEdit.setOnClickListener(v -> listener.onEditClicked(pwd));
            btnDelete.setOnClickListener(v -> listener.onDeleteClicked(pwd));
        }
    }
}

