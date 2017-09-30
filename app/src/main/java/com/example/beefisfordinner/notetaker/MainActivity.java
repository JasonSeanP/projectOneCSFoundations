package com.example.beefisfordinner.notetaker;
//Imported android classes
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

//Imported java input/output classes
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//Public MainActivity class
public class MainActivity extends AppCompatActivity {
    EditText EditText1; //Declaring our EditText variable
    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate method for when the app is loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //Declaring our FAB (Save icon)
        fab.setOnClickListener(new View.OnClickListener() { //When button is clicked...
            @Override
            public void onClick(View view) { //Call the save function for saving the note
                Save("Note1.txt");
            }
        });

        EditText1 = (EditText) findViewById(R.id.EditText1); //Set EditText1 variable to the one we called in the content_main
        EditText1.setText(Open("Note1.txt"));
    }

    //Open menu when the icon is selected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Funciton to save string from the note we wrote down
    public void Save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(EditText1.getText().toString());
            out.close(); //Close output stream function
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show(); //If note is saved, display this message
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show(); //Else, show error
        }
    }

    //Function for loading string/note
    public String Open(String fileName) {
        String content = "";
        if (FileExists(fileName)) { //If the file actually exists...
            try {
                InputStream in = openFileInput(fileName); //Load it
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) { //Else display error
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    //Function to check whether the file we want to load even exists
    public boolean FileExists(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}