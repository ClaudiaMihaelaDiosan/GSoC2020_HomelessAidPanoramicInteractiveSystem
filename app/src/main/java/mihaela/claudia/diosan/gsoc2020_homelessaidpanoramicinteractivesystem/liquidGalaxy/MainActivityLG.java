package mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.R;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.adapters.CardsAdapter;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.lg_connection.LGCommand;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.lg_connection.LGConnectionManager;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.utils.Cards;

public class MainActivityLG extends AppCompatActivity {

   /* MaterialButton test;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lg);
       /* test = findViewById(R.id.test);*/

        buildRecyclerView();

    /*    test.setOnClickListener(v -> {
            try {
                String sentence = "/home/lg/bin/lg-relaunch > /home/lg/log.txt";
                showAlertAndExecution(sentence, "relaunch");
            } catch (Exception e) {
                Toast.makeText(this, getResources().getString(R.string.error_chip), Toast.LENGTH_LONG).show();
            }
        });*/
    }


    private void buildRecyclerView(){
        final RecyclerView recyclerView = findViewById(R.id.recycler_view_first_lg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final List<Cards> cards = new ArrayList<>();

        cards.add(new Cards(R.drawable.baseline_location_city_white_48,getString(R.string.cities), getColor(R.color.color_first_card)));
        cards.add(new Cards(R.drawable.baseline_bar_chart_white_48dp, getString(R.string.statistics), getColor(R.color.color_second_card)));
        cards.add(new Cards(R.drawable.baseline_public_white_48dp, getString(R.string.demo), getColor(R.color.color_third_card)));
        cards.add(new Cards(R.drawable.baseline_directions_white_48dp, getString(R.string.tour), getColor(R.color.color_forth_card)));
        final CardsAdapter cardsAdapter = new CardsAdapter(cards);
        recyclerView.setAdapter(cardsAdapter);
    }

  /*  *//*SHUT DOWN, RELAUNCH and REBOOT*//*
    private void showAlertAndExecution(final String sentence, String action) {
        // prepare the alert box
        MaterialAlertDialogBuilder alertbox = new MaterialAlertDialogBuilder(this);

        // set the message to display
        alertbox.setMessage("Are you sure to " + action + " Liquid Galaxy?");

        // set a positive/yes button and create a listener
        // When button is clicked
        alertbox.setPositiveButton("OK", (arg0, arg1) -> {
            LGConnectionManager.getInstance().addCommandToLG(new LGCommand(sentence, LGCommand.CRITICAL_MESSAGE, null));
        });

        // set a negative/no button and create a listener
        // When button is clicked
        alertbox.setNegativeButton("CANCEL", (arg0, arg1) -> {
        });
        // display box
        alertbox.show();
    }
*/
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
                .setPositiveButton(getString(R.string.lg_about_button), (dialog, id) -> dialog.dismiss())
                .create();

        alert.show();
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right );
    }
}
