package com.example.update_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The MainActivity creates a Shopping List using a ArrayList with a RecyclerView.
 * The RecyclerView in combination with an Adapter is the recommended way for creating lists in Android.
 * It is possible to edit an item of the shopping list by clicking on a specific view.
 *
 * The documentation focuses on the click on the item view and the activity for editing an item.
 * For Javadoc information about the RecyclerView and Adapter check out the specific project.
 *
 * Layout File: activity_main.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> itemList;
    private Adapter adapter;
    private Item selectedListItem;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        fillItemList();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new Adapter(itemList);
        recyclerView.setAdapter(adapter);

        // The activityResultLauncher is needed because the EditActivity should give back a certain result
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                /**
                 * The intent collects the extra from the intent of the EditActivity that led back to the MainActivity.
                 * A switch case checks the result code from the previous Activity.
                 * There should not be any action if the result code is RESULT_CANCELED.
                 * The variables of the item get updated with the user input for the result code RESULT_OK.
                 * @param result is the result code that comes from the previous Activity.
                 */
                @Override
                public void onActivityResult(ActivityResult result) {

                    Intent data = result.getData();

                    switch (result.getResultCode()) {

                        case RESULT_CANCELED:
                            break;

                        case RESULT_OK:
                            selectedListItem.changeItem(data.getStringExtra(EditActivity.EXTRA_ITEM));
                            selectedListItem.changeQuantity(data.getStringExtra(EditActivity.EXTRA_QUANTITY));
                            adapter.notifyDataSetChanged();
                            break;
                    }
                }
        });

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {

            /**
             * The Adapter calls an onItemClickListener that handles the click on an item.
             * Clicking on the item view initializes the selectedListItem.
             * A new Intent from the MainActivity to the EditActivity gets created and called.
             * @param item is the item of the RecyclerView list that was clicked on.
             */
            @Override
            public void onItemClick(Item item) {

                selectedListItem = item;

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    private void fillItemList() {

        itemList.add(new Item("Noodles", "1"));
        itemList.add(new Item("Cheese", "1"));
        itemList.add(new Item("Pepper", "2"));
        itemList.add(new Item("Onions", "4"));
        itemList.add(new Item("Carrots", "2"));
        itemList.add(new Item("Tomato", "1"));
        itemList.add(new Item("Fish", "4"));
        itemList.add(new Item("Grill Sauce", "1"));
        itemList.add(new Item("Baguette", "1"));
        itemList.add(new Item("Mushrooms", "6"));
        itemList.add(new Item("Cooking Oil", "1"));
    }
}