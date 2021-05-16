package com.example.my_notes.Model;

import android.util.Log;

import com.example.my_notes.DatabaseAdapter;

import java.util.Date;

public class TextNote extends Note {

    //Atributs de la classe
    private final DatabaseAdapter adapter = DatabaseAdapter.databaseAdapter;

    public TextNote(String folderId){
        super(folderId);
    }

    public TextNote(String title, String folderId){
        super(title, folderId);
    }

    public TextNote(String title, String id, String folderId, String owner,
                     Date creation, Date modify){
        super(title, folderId, id, owner, creation, modify);
    }

    public void saveTextNote(){
        Log.d("saveTextNote", "adapter-> saveTextNote");
        adapter.saveTextNote(getTitle(), getId() , getFolderId() , getOwner(),
                getCreation_date(), getModify_date());
    }

    public void updateTextNote(){
        Log.d("updateNote", "adapter-> updateNote");
        adapter.updateTextNote(getTitle(), getFolderId(), getId(), getOwner(), getCreation_date(), new Date());
    }

    public void deleteTextNote(){
        Log.d("deleteNote", "adapter-> deleteNote");
        adapter.deleteTextNote(getId(), getFolderId());
    }
}