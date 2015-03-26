package com.example.lyazidselmi.memorygame;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

    private class MyActivity{
        private String title;
        private Class<? extends Activity> activityClass;

        public MyActivity(int titleResId,Class<? extends Activity> activityClass){
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }

        @Override
        public String toString(){
            return title.toString();
        }
    }

    private static MyActivity[] myActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myActivities = new MyActivity[]{
                new MyActivity(R.string.title_game, GameActivity.class),
                new MyActivity(R.string.title_settings, SettingsActivity.class)
        };

        setListAdapter(new ArrayAdapter<MyActivity>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                myActivities));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        startActivity(new Intent(MainActivity.this,myActivities[position].activityClass));
    }
}




