package com.sivaram.session14assignment2;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    Button saveImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Type Cast Button from view to Java
        saveImageButton = (Button) findViewById(R.id.saveImageButton);

        // Set on Click Listener To Store Image into External Storage
        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check Whether Permission Granted Or Not If Not Request for Write External Storage Permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this,new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }
                }

                // Convert Drawable item as BitMap
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nougat);

                // Read External Storage Folder
                File sd = Environment.getExternalStorageDirectory();
                String fileName = "test.png";
                // Prepare to write Data to external Storage
                File dest = new File(sd, fileName);
                try {
                    // Read Data through fileStream and write in compress mode to external storage.
                    FileOutputStream out;
                    out = new FileOutputStream(dest);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "File Stored To external Storage Successfully.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
