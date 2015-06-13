package mobisandoz.hr.com.sandozapp.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
public class DrawerAdapter extends BaseAdapter {
    private static ArrayList<String> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    public DrawerAdapter(Context context, ArrayList<String> results) {
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

    private int[] slike = new int[]{
        R.drawable.home,
        R.drawable.mapa,
        R.drawable.moji_bodovi,
        R.drawable.zamjeni_bodove,
        R.drawable.kupljenipaketi,
        R.drawable.unesi_kod,
        R.drawable.upute,
        R.drawable.registracija
    };

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.element_drawer, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.itemname);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_itemName.setText(itemDetailsrrayList.get(position));
        holder.itemImage.setImageResource(slike[position]);

        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        ImageView itemImage;
    }
}
