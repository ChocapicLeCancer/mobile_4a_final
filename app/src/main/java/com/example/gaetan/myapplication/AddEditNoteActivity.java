package com.example.gaetan.myapplication;

import android.content.Intent;
import android.database.Observable;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.databinding.ObservableField;

import com.example.gaetan.myapplication.databinding.ActivityAddNoteBinding;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.gaetan.myapplication.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "ccom.example.gaetan.myapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.gaetan.myapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.gaetan.myapplication.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    NumberPicker Numberpicker;
    public ObservableField<String> edit_text_title = new ObservableField<>() ;
    public ObservableField<String> edit_text_description = new ObservableField<>() ;
    public ObservableField<Integer> number_picker_priority = new ObservableField<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        edit_text_title.set(edit_text_title.get());
        edit_text_description.set(edit_text_description.get());
        number_picker_priority.set(number_picker_priority.get());

        super.onCreate(savedInstanceState);

        ((ActivityAddNoteBinding)DataBindingUtil.setContentView(this, R.layout.activity_add_note)).setViewModel(this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editer Note");
            edit_text_title.set(intent.getStringExtra(EXTRA_TITLE));
            edit_text_description.set(intent.getStringExtra(EXTRA_DESCRIPTION));
            number_picker_priority.set(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("ajouter Note");
        }
    }

    private void saveNote() {

        String title = edit_text_title.get();
        String description = edit_text_description.get();
        int priority = number_picker_priority.get();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "mettez un tittre et une description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
/* noteadao + database => chamber
   note repesotory     => repesitory
   view model nete view model
   activity main
   xml view

**/