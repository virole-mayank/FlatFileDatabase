package com.example.flatfiledatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    TextView Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setSubtitle("Developed by Mayank Virole");
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.contact);
        e3 = findViewById(R.id.email);
        Text = findViewById(R.id.text);
    }

    public void save(View v)
    {
        String text = "   " + e1.getText().toString() + " \n" + "   "+e2.getText().toString() + " \n" + "   "+e3.getText().toString();
        FileOutputStream fos = null;
        try{
            fos = openFileOutput("entry.txt",MODE_PRIVATE);
            fos.write(text.getBytes());
            e1.getText().clear();
            e2.getText().clear();
            e3.getText().clear();
            Toast.makeText(this, "saved to " + getFilesDir() + "entry.txt", Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if(fos!= null)
            {
                try{
                    fos.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v)
    {
        FileInputStream fis = null;
        try{
            fis = openFileInput("entry.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine())!= null){
                sb.append(text).append("\n");
            }
            Text.setText(sb.toString());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if(fis!= null)
            {
                try{
                    fis.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}