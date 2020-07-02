package mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.MainActivity;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.R;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.lg_connection.LGCommand;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.lg_connection.LGConnectionManager;

public class FirstActivityLG extends AppCompatActivity {

    MaterialButton test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_l_g);
        test = findViewById(R.id.test);

        test.setOnClickListener(v -> {
            try {
                String sentence = "/home/lg/bin/lg-relaunch > /home/lg/log.txt";
                showAlertAndExecution(sentence, "relaunch");
            } catch (Exception e) {
                Toast.makeText(this, getResources().getString(R.string.error_chip), Toast.LENGTH_LONG).show();
            }
        });
    }

    /*SHUT DOWN, RELAUNCH and REBOOT*/
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
