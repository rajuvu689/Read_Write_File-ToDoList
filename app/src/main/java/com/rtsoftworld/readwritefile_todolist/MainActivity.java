package com.rtsoftworld.readwritefile_todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button saveButton;
    private EditText enterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = findViewById(R.id.saveButton);
        enterText = findViewById(R.id.enterText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enterText.getText().toString().equals("")){
                    String message = enterText.getText().toString();
                    writeToFile(message);
                }
                else {
                    //do nothing
                }

            }
        });

        try {
            if (readFromFile() != null){
                enterText.setText(readFromFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeToFile(String message){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("toDoList.txt", Context.MODE_PRIVATE)); //write to file
            outputStreamWriter.write(message);
            outputStreamWriter.close(); //always close Stream Writer
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() throws IOException {
        String result = "";

        InputStream inputStream = openFileInput("toDoList.txt"); // open file

        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream); //read the text file
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  // used to read data line by line by readLine() method

            String tempString = ""; // string for storing string from txt file
            StringBuilder stringBuilder = new StringBuilder();

            while ((tempString = bufferedReader.readLine()) != null){
                stringBuilder.append(tempString);
            }

            inputStream.close();
            result = stringBuilder.toString(); // store string from txt file to result
            
        }

        return result;
    }
}