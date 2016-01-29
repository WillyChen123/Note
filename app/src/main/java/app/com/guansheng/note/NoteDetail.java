package app.com.guansheng.note;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class NoteDetail extends AppCompatActivity {
    int no;
    String note;
    EditText editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail);

        Intent intent = getIntent();
        no = intent.getIntExtra("no", 0);
        note = intent.getStringExtra("note");
        editNote = (EditText) findViewById(R.id.editNote);
        editNote.setText(note);
    }
}
