package com.example.beefisfordinner.notetaker;

//Imported android tools for the NotesAdapter
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BeefIsForDinner on 10/22/17.
 */

//Public class declaration for the adapter
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

//Private declaration for the list of notes
private List<NotesBuilder> notesList;


public class MyViewHolder extends RecyclerView.ViewHolder {
    //This is the title and content of the notes that are declared as public variables
    public TextView title, content;

    //Pulling the data for the title and content
    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);

    }
}

    public NotesAdapter(List< NotesBuilder> notesList) {
        this.notesList = notesList;
    }

    //Placeholder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    //This gets the position of the note in the list and sets the corresponding title/content to it
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NotesBuilder note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
    }

    //Returns the amount of items/notes in the list
    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
