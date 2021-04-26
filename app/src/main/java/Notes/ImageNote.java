package Notes;

import android.util.Log;

import com.example.my_notes.DatabaseAdapter;

import java.io.Serializable;
import java.util.Date;


public class ImageNote extends Note {

    private final DatabaseAdapter adapter = DatabaseAdapter.databaseAdapter;

    public ImageNote(String folderId){
        super(folderId);
    }

    public ImageNote(String title, String folderId){
        super(title, folderId);
    }

    public ImageNote(String title, String id, String folderId, String owner,
                     Date creation, Date modify){
        super(title, folderId, id, owner, creation, modify);
    }

    public void saveImageNote(){
        Log.d("saveImageNote", "adapter-> saveImageNote");
        adapter.saveImageNote(getTitle(), getId() , getFolderId() , getOwner(),
                getCreation_date(), getModify_date());
    }

}