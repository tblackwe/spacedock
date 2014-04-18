
package com.funnyhatsoftware.spacedock.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.funnyhatsoftware.spacedock.R;
import com.funnyhatsoftware.spacedock.data.Universe;
import com.funnyhatsoftware.spacedock.fragment.BrowseListFragment;
import com.funnyhatsoftware.spacedock.fragment.DetailsFragment;
import com.funnyhatsoftware.spacedock.fragment.DisplaySquadFragment;
import com.funnyhatsoftware.spacedock.fragment.ManageSquadsFragment;
import com.funnyhatsoftware.spacedock.fragment.SetItemListFragment;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Base fragment managing Activity class, supporting ActionBar spinner
 * navigation. This activity manages all of the fragment transitions to navigate
 * between building squads and browsing items.
 */
public class RootActivity extends PanedFragmentActivity implements ActionBar.OnNavigationListener,
        BrowseListFragment.BrowseTypeSelectionListener,
        SetItemListFragment.SetItemSelectedListener,
        ManageSquadsFragment.SquadSelectListener,
        DisplaySquadFragment.SquadDisplayListener {
    private final String SAVE_NAV_POSITION = "navPos";
    private int mPosition;

    private final String TAG_ITEM_LIST = "itemlist";
    private final String TAG_DISPLAY_SQUAD = "displaysquad";
    private final String TAG_MANAGE_SQUADS = "managesquads";
    private final String TAG_BROWSE_LIST = "browselist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(
                getActionBar().getThemedContext(),
                R.array.action_spinner_list,
                android.R.layout.simple_spinner_dropdown_item);
        getActionBar().setListNavigationCallbacks(spinnerAdapter, this);

        if (savedInstanceState == null) {
            initializePrimaryFragment(new BrowseListFragment(), TAG_BROWSE_LIST);
            mPosition = 0;
        } else {
            mPosition = savedInstanceState.getInt(SAVE_NAV_POSITION);
        }

        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            boolean worked = false;
            try {
                Log.i("spacedock", "want to open " + data.getPath() + " " + data.getScheme());
                String scheme = data.getScheme();
                InputStream is = null;
                if (scheme.equals("file")) {
                    File squadFile = new File(data.getPath());
                        is = new FileInputStream(squadFile);
                } else if (scheme.equals("content")) {
                    is = getContentResolver().openInputStream(data);
                }
                Universe.getUniverse().loadSquadsFromStream(is, true);
                is.close();
                worked = true;
            } catch (FileNotFoundException e) {
            } catch (JSONException e) {
            } catch (IOException e) {
            }
            if (worked) {
                Toast.makeText(this, "Imported squads.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to import squads.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Universe universe = Universe.getUniverse();
        try {
            universe.save(this);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_NAV_POSITION, mPosition);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        if (itemPosition == mPosition)
            return false;

        Fragment newPrimaryFragment;
        String tag;
        if (itemPosition == 0) {
            newPrimaryFragment = new BrowseListFragment();
            tag = TAG_BROWSE_LIST;
        } else {
            newPrimaryFragment = new ManageSquadsFragment();
            tag = TAG_MANAGE_SQUADS;
        }
        Fragment oldSecondaryFragment = null;
        if (isTwoPane()) {
            oldSecondaryFragment = getSupportFragmentManager()
                    .findFragmentById(R.id.secondary_fragment_container);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.primary_fragment_container, newPrimaryFragment, tag);
        if (oldSecondaryFragment != null) {
            transaction.remove(oldSecondaryFragment);
        }
        transaction.commit();

        mPosition = itemPosition;
        return true;
    }

    @Override
    public void onBrowseTypeSelected(String itemType) {
        Fragment newFragment = SetItemListFragment.newInstance(itemType);
        navigateToSubFragment(newFragment, TAG_ITEM_LIST);
    }

    @Override
    public void onItemSelected(String itemType, String itemId) {
        DetailsFragment fragment = DetailsFragment.newInstance(itemType, itemId);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (isTwoPane()) {
            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // show the dialog.
            fragment.show(ft, "dialog");
        } else {
            // single pane, add new fragment in place of main
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.primary_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onSquadSelected(int squadIndex) {
        Fragment newFragment = DisplaySquadFragment.newInstance(squadIndex);
        navigateToSubFragment(newFragment, TAG_DISPLAY_SQUAD);
    }

    @Override
    public void onSquadEditAction(int squadIndex) {
        Intent intent = new Intent(this, EditSquadActivity.class);
        intent.putExtra(EditSquadActivity.EXTRA_SQUAD_INDEX, squadIndex);
        startActivity(intent);
    }
}
