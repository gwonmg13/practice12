package kr.soen.practice12;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {


    CheckBox stamp;
    myCanvas canvas;


    public String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/";
        } else
            sdPath = getFilesDir() + "";
        return sdPath;
    }

    public void myClick(View v) {

        if (v.getId() == R.id.button) {
            canvas.setOperation("clear");

        } else if (v.getId() == R.id.button2) {
            canvas.setOperation("open");

        } else if (v.getId() == R.id.button3) {
            canvas.setOperation("save");
            canvas.path = getFilesDir() + "";

        } else if (v.getId() == R.id.button4) {
            stamp.setChecked(true);
            canvas.IsChecked = true;
            canvas.setOperation("rotate");

        } else if (v.getId() == R.id.button5) {
            canvas.IsChecked = true;
            stamp.setChecked(true);
            canvas.setOperation("move");

        } else if (v.getId() == R.id.button6) {
            canvas.IsChecked = true;
            stamp.setChecked(true);
            canvas.setOperation("scale");

        } else if (v.getId() == R.id.button7) {
            canvas.IsChecked = true;
            stamp.setChecked(true);
            canvas.setOperation("skew");

        }
    }
    int check = 0;
    int check_blur = 0;
    int check_color = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stamp = (CheckBox) findViewById(R.id.checkBox);
        canvas = (myCanvas) findViewById(R.id.canvas);

        stamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stamp.setChecked(isChecked);
                canvas.IsChecked = isChecked;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Bluring").setCheckable(true);
        menu.add(0, 2, 0, "Coloring").setCheckable(true);
        menu.add(0, 3, 0, "Pen Width Big").setCheckable(true);
        menu.add(0, 4, 0, "Pen Color Red");
        menu.add(0, 5, 0, "Pen Color Blue");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            check_blur++;
            if (check_blur % 2 == 1) {
                canvas.setOperation("bluring");
                item.setChecked(true);
            } else {
                canvas.setOperation("nofilter");
                item.setChecked(false);
            }

        } else if (item.getItemId() == 2) {
            check_color++;
            if (check_color % 2 == 1) {
                item.setChecked(true);
                canvas.setOperation("coloring");

            } else {
                canvas.setOperation("nofilter");
                item.setChecked(false);
            }

        } else if (item.getItemId() == 3) {
            check++;
            if (check % 2 == 1) {
                item.setChecked(true);
                canvas.setOperation("big");
            } else {
                item.setChecked(false);
                canvas.setOperation("small");
            }
        } else if (item.getItemId() == 4) {
            canvas.setOperation("red");
        } else if (item.getItemId() == 5) {
            canvas.setOperation("blue");
        }
        return super.onOptionsItemSelected(item);
    }
}