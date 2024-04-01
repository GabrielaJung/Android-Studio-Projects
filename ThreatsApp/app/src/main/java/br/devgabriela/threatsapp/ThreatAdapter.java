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

    public ThreatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Threat> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourse = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResourse, parent, false);

        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        TextView txtDate = convertView.findViewById(R.id.txtDate);

        txtDescription.setText(getItem(position).getDescription());
        txtDate.setText(getItem(position).getDate());

        return convertView;
    }
}
