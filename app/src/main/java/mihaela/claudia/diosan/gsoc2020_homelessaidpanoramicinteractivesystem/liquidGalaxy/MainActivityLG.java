package mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.MainActivity;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.R;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.lg_navigation.POI;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.lg_navigation.POIController;

public class MainActivityLG extends AppCompatActivity implements View.OnClickListener {

  MaterialCardView cities, statistics, demo, tour;

    private static final POI EARTH_POI = new POI()
            .setLongitude(10.52668d)
            .setLatitude(40.085941d)
            .setAltitude(0.0d)
            .setHeading(0.0d)
            .setTilt(0.0d)
            .setRange(10000000.0d)
            .setAltitudeMode("relativeToSeaFloor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lg);

        initViews();

        cities.setOnClickListener(this);
        statistics.setOnClickListener(this);
        demo.setOnClickListener(this);
        tour.setOnClickListener(this);
    }


    private void initViews(){
        cities = findViewById(R.id.cities_cv);
        statistics = findViewById(R.id.statistics_cv);
        demo = findViewById(R.id.demo_cv);
        tour = findViewById(R.id.tour_cv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cities_cv:
                startActivity(new Intent(MainActivityLG.this, CititesActivity.class));
                POIController.getInstance().moveToPOI(EARTH_POI, null);
                break;
            case R.id.statistics_cv:
                MainActivity.showSuccessToast(this,"Statistics");
                break;
            case R.id.demo_cv:
                MainActivity.showSuccessToast(this,"Demo");
                break;
            case R.id.tour_cv:
                MainActivity.showSuccessToast(this,"Tour");
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lg_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_lg:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_tools_lg:
                startActivity(new Intent(this, ToolsActivity.class));
                return true;
            case R.id.help_lg:
                startActivity(new Intent(this, HelpActivity .class));
                return true;
            case R.id.action_about_lg:
               showAboutDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAboutDialog() {

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.lg_about_dialog, null);

        androidx.appcompat.app.AlertDialog alert = new MaterialAlertDialogBuilder(this)
                .setTitle(getResources().getString(R.string.lg_about_title))
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.lg_about_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .create();

        alert.show();
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right );
    }


}
