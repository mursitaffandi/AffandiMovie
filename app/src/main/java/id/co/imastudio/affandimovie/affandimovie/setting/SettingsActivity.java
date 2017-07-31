package id.co.imastudio.affandimovie.affandimovie.setting;


import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.global.PreferenceSettingOrder;
import id.co.imastudio.affandimovie.affandimovie.util.MessageEvent;

public class SettingsActivity extends AppCompatActivity {
    private static String indexSelected = "";

    private static final Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            indexSelected = stringValue;
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            }
            return true;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_with_actionbar);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new PreferenceDataShow()).commit();
    }

    public static class PreferenceDataShow extends PreferenceFragment {
        ListPreference listPreferenceOrder;
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            listPreferenceOrder = (ListPreference) findPreference("example_list");
            if (PreferenceSettingOrder.STATE_ORDER == 0) {
                listPreferenceOrder.setValueIndex(0);
            }
            if (PreferenceSettingOrder.STATE_ORDER == 1) {
                listPreferenceOrder.setValueIndex(1);
            }
            if (PreferenceSettingOrder.STATE_ORDER == 2){
                listPreferenceOrder.setValueIndex(2);
            }
            bindPreferenceSummaryToValue(findPreference("example_list"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_preference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_confirm) {
            if (Integer.parseInt(indexSelected) != PreferenceSettingOrder.STATE_ORDER){
                PreferenceSettingOrder.STATE_ORDER = Integer.parseInt(indexSelected);
                EventBus.getDefault().post(new MessageEvent(true));
                Log.d("index_selected", indexSelected);
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), "")
        );
    }
}