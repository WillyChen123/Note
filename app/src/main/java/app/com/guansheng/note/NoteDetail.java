package app.com.guansheng.note;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NoteDetail extends AppCompatActivity {
    int no;
    String note;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail);

        Intent intent = getIntent();
        no = intent.getIntExtra("no", 0);
        note = intent.getStringExtra("note");
    }
}
