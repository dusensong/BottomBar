package cn.reflect.bottombar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {
    public static final int POSITION_ITEM_STORES = 0;
    public static final int POSITION_ITEM_SIGN_IN_MGR = 1;
    public static final int POSITION_ITEM_SALE_BRIEF = 2;
    public static final int POSITION_ITEM_SERVICES = 3;
    private static final String TAG = "Main";

    BottomItem[] bottomItems;

    int currentPosition = POSITION_ITEM_STORES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 设置底栏
        initBottomItems();

        // 默认选中
        Log.e(TAG, "selectBottomItem");
        Log.e(TAG, "currentPosition=" + currentPosition);
        selectBottomItem(currentPosition);
    }

    void selectBottomItem(int position) {
        // 设置item被选中状态
        for (int i = 0; i < bottomItems.length; i++) {
            bottomItems[i].setSelected(i == position);
        }

        currentPosition = position;
    }

    void initBottomItems() {
        // BottomItem点击
        View.OnClickListener itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                selectBottomItem(position);
                Toast.makeText(MainActivity.this, "selected position = " + position, Toast.LENGTH_SHORT).show();
            }
        };

        bottomItems = new BottomItem[4];

        for (int i = 0; i < bottomItems.length; i++) {
            BottomItem item = null;
            switch (i) {
                case POSITION_ITEM_STORES: {
                    item = new BottomItem(findViewById(R.id.stores), i, itemClickListener);
                    break;
                }
                case POSITION_ITEM_SIGN_IN_MGR: {
                    item = new BottomItem(findViewById(R.id.sign_in_mgr), i, itemClickListener);
                    break;
                }
                case POSITION_ITEM_SALE_BRIEF: {
                    item = new BottomItem(findViewById(R.id.sale_brief), i, itemClickListener);
                    break;
                }
                case POSITION_ITEM_SERVICES: {
                    item = new BottomItem(findViewById(R.id.value_added_services), i, itemClickListener);
                    break;
                }
            }

            if (item != null) {
                bottomItems[i] = item;
            }
        }
    }

    static class BottomItem {
        public View itemView;

        public BottomItem(View itemView, int position, View.OnClickListener itemClickListener) {
            this.itemView = itemView;
            this.itemView.setTag(position);
            this.itemView.setOnClickListener(itemClickListener);
        }

        public void setSelected(boolean selected) {
            itemView.setEnabled(!selected);
            itemView.setSelected(selected);
        }
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
}
