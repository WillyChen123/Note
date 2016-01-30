package app.com.guansheng.note;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NoteList extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    ListView listView;
    private static final String DB_NAME = "Note.db";
    private static final String TABLE_NAME = "myNote";
    private static final String[] FORM = new String[]{"_id","no","note"};
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list);

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);
        String createTable= "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "no INTEGER, " +
                "note TEXT" +
                ");";
        db.execSQL(createTable);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case RESULT_OK:
                break;
        }
    }
}
