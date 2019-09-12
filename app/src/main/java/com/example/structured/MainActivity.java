package com.example.structured;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    //Create an object of the Action Mode class, will be used to show and hide the action mode contextual menu
    ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {

        @Override
        // done when the action mode contextual menu is created
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actionmode_context_menu, menu);
            return true;
        }

        @Override
        // executes every time the  contextual menu is prepared again
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }


        @Override
        // Handles click events on the action mode menu
        // (add a mode.finish cause the mode needs to be ended before it can be started again)
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.actionModeMenu1:
                    Toast.makeText(MainActivity.this, "Action Mode 1 Clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.actionModeMenu2:
                    Toast.makeText(MainActivity.this, "Action Mode 2 Clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;

        }
    };


    // Set View to Profile Layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);


        // Code to show app to use Toolbar instead of Actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Long Pressing the text will get us the context menu
        TextView tv = findViewById(R.id.textViewMenu);
        this.registerForContextMenu(tv);

        // bind action mode menu to button, set on click listener to button
        Button button = findViewById(R.id.removeBtn);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }
                mActionMode = startActionMode(mActionModeCallBack);
                return true;
            }
        });

        // Gets reference to floating action button and text view
        // Gets current textValue as a string converts it to an integer
        // calls our function doubleTheValue (From the class MyWorker.java)
        // Which doubles the value and converts back to a string
        FloatingActionButton shareBtn = findViewById(R.id.shareButton);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textValue = findViewById(R.id.textValue);
                String stringValue = textValue.getText().toString();
                int originalValue = Integer.parseInt(stringValue);
                int newValue = MyWorker.doubleTheValue(originalValue);
                textValue.setText(Integer.toString(newValue));


                Snackbar.make(view, "Changed value " + originalValue + " to " + newValue,
                        LENGTH_LONG).setAction("Action", null).show();

            }
        });
    }


    //Creating an options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Create an object of the Menu inflater class
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Handling clicks to the options menu
        switch (item.getItemId()) {
            case R.id.menuItem1:
                Toast.makeText(this, "Menu Item 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem2:
                Toast.makeText(this, "Menu Item 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem3:
                Toast.makeText(this, "Menu Item 3 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Creating a floating context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    // Handle clicks on items on floating context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contextMenu1:
                Toast.makeText(this, "Context Menu 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextMenu2:
                Toast.makeText(this, "Context Menu  2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextMenu3:
                Toast.makeText(this, "Context Menu  3 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popUpItem1:
                Toast.makeText(this, "Popup Menu 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.popUpItem2:
                Toast.makeText(this, "Popup Menu  2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.popUpItem3:
                Toast.makeText(this, "Popup Menu  3 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
