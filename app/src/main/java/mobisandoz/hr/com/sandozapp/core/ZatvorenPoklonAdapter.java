package mobisandoz.hr.com.sandozapp.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mobisandoz.hr.com.sandozapp.R;

/**
 * Created by Josip on 17.4.2015..
 */
public class ZatvorenPoklonAdapter extends BaseAdapter {
    private static ArrayList<String> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    public ZatvorenPoklonAdapter(Context context, ArrayList<String> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.elementipokloni, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.itemname);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_itemName.setText(itemDetailsrrayList.get(position));
        holder.itemImage.setImageResource(R.drawable.poklonzat);

        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        ImageView itemImage;
    }
}
