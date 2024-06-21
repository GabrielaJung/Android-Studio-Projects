package br.devgabriela.threatsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ThreatAdapter extends ArrayAdapter<Threat> {
    private Context mContext;
    private int mResourse;

    LayoutInflater inflater;
    ThreatSQLiteDatabase db;

    public ThreatAdapter(@NonNull Context context, int resource, ThreatSQLiteDatabase db) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.db = db;
        this.mResourse = resource;
    }

    public int getCount(){
        return db.getThreats().size();
    }

    public Threat getItem(int position){
        return db.getThreats().get(position);
    }

    public long getItemId(int position){
        return db.getThreats().get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        v = inflater.inflate(R.layout.list_row, null);
        TextView desc = v.findViewById(R.id.txtDescription);
        TextView date = v.findViewById(R.id.txtDate);
        desc.setText(db.getThreats().get(position).getDescription());
        date.setText(db.getThreats().get(position).getDate());
        return v;
    }
}

