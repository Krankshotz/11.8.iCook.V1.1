/*
Text scanner applet
Paul Figueroa
updated: 12/1/17
 */
package com.example.jordan.icook;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;
import static com.google.android.gms.vision.CameraSource.Builder;
import static com.google.android.gms.vision.CameraSource.CAMERA_FACING_BACK;
import static com.google.android.gms.vision.CameraSource.PictureCallback;
import static com.google.android.gms.vision.CameraSource.ShutterCallback;



public class receipt_Scanner extends AppCompatActivity {
    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    String fileName="Saved Receipt", stringTotal, itemName="", itemQuant="";
    String[] parts,foods;
    final int RequestCameraPermissionID = 1001;
    int foodID;
    File path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    File file=new File(path,fileName);
    public receipt_Scanner() throws FileNotFoundException {}
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt__scanner);
        cameraView = findViewById(R.id.surface_view);
        textView =  findViewById(R.id.text_view);
        final Button takePicButt =  findViewById(R.id.picButt);
        myDb = new DatabaseHelper(this);
        foods = getResources().getStringArray(R.array.approved_food_list); //creates food list to check for pantry input


        //show path of downloads for testing purposes
        Toast.makeText(getBaseContext(), "Save path: "+path.toString(),Toast.LENGTH_SHORT).show();

        final CameraSource.ShutterCallback shutter = new ShutterCallback() {
            @Override
            public void onShutter() {}
        };
        final CameraSource.PictureCallback pcb = new PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes) {}
        };
        //capture text from camera
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {//if textRecognizer fails
            Log.w("MainActivity", "Detector dependencies are not yet available");
        } else {//on textRecognizer success
            cameraSource = new Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(30.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(receipt_Scanner.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });
            //updates screen on detections

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release(){}
                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections){
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size()!=0){
                        textView.post(new Runnable() {
                            @Override
                            public void run(){
                                StringBuilder stringBuilder = new StringBuilder(); //build a string file with all items
                                for(int i=0;i<items.size();++i){
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                }
                                textView.setText(stringBuilder.toString()); //set display to items on cam
                                stringTotal=stringBuilder.toString(); //save to string var
                            }
                        });
                    }
                }
            });
            takePicButt.setOnClickListener(
                    new View.OnClickListener(){
                        public void onClick(View v1) {
                            //try writing string to file
                            try (FileOutputStream stream = new FileOutputStream(file)){stream.write(stringTotal.getBytes());}
                            catch (Exception e){e.printStackTrace();}
                            //try inputting file to scanner for parsing
                            try (Scanner scanner=new Scanner(file)){
                                while (scanner.hasNextLine()) {//while not eof input into pantry db
                                    parts = scanner.nextLine().split("\\s+");//here is where it should check before adding items
                                    if(Arrays.asList(foods).contains(parts[0])) {  //check if in approved foods list
                                        itemName = parts[0];
                                        itemName=itemName.replaceAll("[^A-Za-z]+", "");//remove all but alpha chars
                                        itemName=itemName.substring(0, 1).toUpperCase()+itemName.substring(1).toLowerCase(); //capitalizes first letter and lower rest
                                        itemQuant =  parts[1];
                                        myDb.insertUpdate(itemName, Integer.parseInt(itemQuant));
                                    }
                                }
                            } catch (FileNotFoundException e){e.printStackTrace();}
                            Toast.makeText(getBaseContext(),"Items Captured!",Toast.LENGTH_SHORT).show();

                        }
                    }
            );//on capture screen press
        }
        //the following is a test

    }

}
