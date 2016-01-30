package app.com.guansheng.note;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class NoteList extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    ListView listView;
    private static final String DB_NAME = "Note.db";
    private static final String TABLE_NAME = "myNote";
    private static final String[] FORM = new String[]{"_id","no","note"};
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter adapter;

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

        cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor.getCount()==0){
            add(0,"按一下編輯記事",0);
            add(1,"長按清除記事",1);
            add(2,"",2);
            add(3,"",3);
            add(4,"",4);
            add(5,"",5);
        }

        adapter = new SimpleCursorAdapter(this,R.layout.note_item,cursor,FORM,new int[] {R.id.note_id,R.id.note_no,R.id.note},0);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        relist();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cursor.moveToPosition(position);
        String note = cursor.getString(cursor.getColumnIndex(FORM[2]));
        Intent intent = new Intent(this,NoteDetail.class);
        intent.putExtra("no", position);
        intent.putExtra("note", note);
        startActivityForResult(intent, position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        update(position,"",position);
        relist();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case RESULT_OK:
                break;
        }
    }

    private void add(int no,String note,int _id){
        ContentValues cv = new ContentValues(3);
        cv.put(FORM[0],_id);
        cv.put(FORM[1], no);
        cv.put(FORM[2], note);
        db.insert(TABLE_NAME, null, cv);
    }

    private void update(int no,String note,int _id){
        ContentValues cv = new ContentValues(3);
        cv.put(FORM[0],_id);
        cv.put(FORM[1],no);
        cv.put(FORM[2],note);
        db.update(TABLE_NAME, cv, "_id=" + _id, null);
    }

    private void relist(){
        cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        adapter.changeCursor(cursor);
    }



}
