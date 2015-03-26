package com.example.lyazidselmi.memorygame;

        import android.content.Context;
        import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AbsListView;
        import android.widget.BaseAdapter;
        import android.widget.GridView;
        import android.widget.ImageView;

/**
 * Created by lyazid.selmi on 24/03/2015.
 */
public class ImageAdapter extends BaseAdapter{

    private Integer[] cardIDs;
    private Context context;
    public ImageAdapter(Context context, Integer[] cardIds){
        this.context = context;
        this.cardIDs = cardIds;
    }

    @Override
    public int getCount() {
        return cardIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardFlipView imageView;
        if (convertView == null) {
            imageView = new CardFlipView(context,cardIDs[position]);
            Log.i("MEMORY GAME",parent.getClass().toString());
            //Log.i("MEMORY GAME",convertView.getClass().toString());
            int w = ((GridView)parent).getWidth() / 3 - 10;
            int h = ((GridView)parent).getHeight() / 2 - 10;
            Log.i("MEMORY GAME Width",String.valueOf(w));
            Log.i("MEMORY GAME Height",String.valueOf(h));
            imageView.setLayoutParams(new GridView.LayoutParams(-10,-10));
            //imageView.setPadding(5, 5, 5, 5);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (CardFlipView) convertView;
        }
        imageView.setImageResource(cardIDs[position]);
        return imageView;
    }
}
