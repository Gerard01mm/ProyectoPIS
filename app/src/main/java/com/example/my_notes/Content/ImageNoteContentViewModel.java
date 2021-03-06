package com.example.my_notes.Content;

import android.app.AlertDialog;
import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.my_notes.DatabaseAdapter;

import java.util.ArrayList;

import com.example.my_notes.Model.ImageNoteContent;
import com.example.my_notes.Model.NotesContent;
import com.google.android.material.textfield.TextInputEditText;


public class ImageNoteContentViewModel extends ViewModel implements DatabaseAdapter.vmInterface {

    private DatabaseAdapter adapter;
    private MutableLiveData<String> mToast;
    private String noteId, folderId;
    private Application application;
    private MutableLiveData<NotesContent> imageNoteContent;

    public ImageNoteContentViewModel(Application application, String noteId, String folderId){
        this.adapter= new DatabaseAdapter(this);
        this.mToast = new MutableLiveData<>();
        this.imageNoteContent = new MutableLiveData<>();
        this.application = application;
        this.noteId = noteId;
        this.folderId = folderId;
        adapter.getImageNoteContent(noteId, folderId);
    }

    public void saveImageNoteContent(String path, String text, String idNote, String idFolder){
        ImageNoteContent im = new ImageNoteContent(idNote, idFolder, text, path);
        imageNoteContent.setValue(im);
        if (path == null){
            im.updateContent();
        }else{
            im.saveContent();
        }
    }

    public MutableLiveData<String> getmToast() {
        return mToast;
    }

    public MutableLiveData<NotesContent> getImageNoteContent() {
        return imageNoteContent;
    }

    public void checkEmail(String idUser, String noteId, String noteFolderId, String textWriten, String title, TextInputEditText userEmail, AlertDialog content){
        adapter.checkEmailImatge(idUser, noteId, noteFolderId, textWriten, title, userEmail, content);
    }
    @Override
    public void setCollection(ArrayList ac) {
        imageNoteContent.setValue((ImageNoteContent) ac.get(0));
    }

    @Override
    public void setToast(String s) {
        this.mToast.setValue(s);
    }
}
