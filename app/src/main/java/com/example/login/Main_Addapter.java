package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by BILLY-PC on 09/02/60.
 */

class Mian_Addapter extends BaseAdapter {
    private Context context;
    private String[] namsString,ImgString;
    private TextView textView;
    private ImageView imageView;

    public Mian_Addapter(Context context,
                      String[] namsString,
                      String[] imgString) {
        this.context = context;
        this.namsString = namsString;
        ImgString = imgString;
    }

    @Override
    public int getCount() {
        return namsString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.main_listview,viewGroup,false);

        textView = (TextView) view1.findViewById(R.id.textView9);
        imageView = (ImageView) view1.findViewById(R.id.imageView3);

        textView.setText(namsString[i]);
        Picasso.get().load(ImgString[i]).into(imageView);



        return view1;
    }
}