package com.example.update_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The Adapter acts like a bridge between the ArrayList and the RecyclerView.
 * It gets data from an Array and converts it to views which can be later placed in the RecyclerView.
 *
 * Layout File for one view: list_item.xml
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Item> itemList;
    private OnItemClickListener listener;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item;
        private TextView quantity;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            item = itemView.findViewById(R.id.text_item);
            quantity = itemView.findViewById(R.id.text_quantity);

            // The itemView calls the onClickListener method that handles the listener with the click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // The edit functionality should not work if the item is in a delete animation
                    if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {

                        // The position of the click gets extracted
                        listener.onItemClick(itemList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public Adapter(ArrayList<Item> itemList) {

        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item currentItem = itemList.get(position);
        holder.item.setText(currentItem.getItem());
        holder.quantity.setText("Quantity: " +currentItem.getQuantity());
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    /**
     * This interface has a method for the click on a specific item.
     */
    public interface OnItemClickListener {

        void onItemClick(Item item);
    }

    /**
     * The method can set a OnItemClickListener to the existing listener.
     * @param listener is a OnItemClickListener.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }
}
