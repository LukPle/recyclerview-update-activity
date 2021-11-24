package com.example.update_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The EditActivity is an special activity that gets called via a click on a certain item of the Shopping List.
 * This Activity allows the user to edit the selected item.
 *
 * Layout File: activity_edit.xml
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    // These are variables that are used for referencing an extra in an intent
    public static final String EXTRA_ITEM = "com.example.update_activity.EXTRA_ITEM";
    public static final String EXTRA_QUANTITY = "com.example.update_activity.EXTRA_QUANTITY";

    private EditText editItem;
    private EditText editQuantity;
    private Button cancel;
    private Button update;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editItem = findViewById(R.id.edit_item);
        editQuantity = findViewById(R.id.edit_quantity);
        cancel = findViewById(R.id.cancel);
        update = findViewById(R.id.update);

        cancel.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    /**
     * This method extracts text from the EditText fields.
     * Afterwards the text gets transferred via an extra of an intent.
     */
    private void updateItem() {

        String item = editItem.getText().toString();
        String quantity = editQuantity.getText().toString();

        if (item.trim().isEmpty() || quantity.trim().isEmpty()) {

            Toast.makeText(this, "Please insert item and quantity", Toast.LENGTH_LONG).show();
        }
        else {

            intent.putExtra(EXTRA_ITEM, item);
            intent.putExtra(EXTRA_QUANTITY, quantity);

            // Result code is RESULT_OK which gets handled in the MainActivity
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * This method handles the clicks on the Buttons.
     * Every Buttons also gives a different result to the intent.
     * This result is important for the MainActivity.
     * @param view is a standard parameter that is used for getting the id of the clicked item.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        intent = new Intent(EditActivity.this, MainActivity.class);

        switch(view.getId()) {

            case R.id.cancel:
                // Result code is RESULT_CANCELED which gets handled in the MainActivity
                setResult(RESULT_CANCELED, intent);
                finish();
                break;

            case R.id.update:
                updateItem();
                break;
        }
    }
}