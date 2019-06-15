package android.example.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.example.quakereport.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuakeAdapter extends ArrayAdapter<EarthQuake> {

    String primaryLocation;
    String locationOffset;
    private static final String LOCATION_SEPARATOR = " of ";
    public QuakeAdapter(Context context, ArrayList<EarthQuake> quakeAdapter) {
        super(context, 0, quakeAdapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quake_list, parent, false);
        }

        final EarthQuake local_object = getItem(position);

        String originalLocation = local_object.getLocation();
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        TextView magView = listItemView.findViewById(R.id.mag);
        magView.setText(decimalFormat.format(local_object.getMag()));
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(local_object.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        TextView offsetView = listItemView.findViewById(R.id.offset);
        offsetView.setText(locationOffset);
        TextView locationView = listItemView.findViewById(R.id.location);
        locationView.setText(primaryLocation);
        TextView dateView = listItemView.findViewById(R.id.date);
        Date date = new Date(local_object.getmTimeInMillisec());
        dateView.setText(dateFormat.format(date));
        TextView timeView = listItemView.findViewById(R.id.time);
        timeView.setText(timeFormat.format(date));
        return listItemView;
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
