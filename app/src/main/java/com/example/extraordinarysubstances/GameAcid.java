package com.example.extraordinarysubstances;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameAcid extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeftBottom;
    public int numRightBottom;
    Array array = new Array();
    Random random = new Random();
    public int count =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        final ImageView img_left_bottom = (ImageView)findViewById(R.id.img_left_bottom);
        img_left_bottom.setClipToOutline(true);

        final ImageView img_right_bottom = (ImageView)findViewById(R.id.img_right_bottom);
        img_right_bottom.setClipToOutline(true);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameAcid.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
                dialog.dismiss();
            }
        });

        Button btn_continue = (Button) dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameAcid.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
                dialogEnd.dismiss();
            }
        });

        Button btn_continue2 = (Button) dialogEnd.findViewById(R.id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameAcid.this, MainActivity.class);
                    startActivity(intent);

                }catch (Exception e){

                }

                dialogEnd.dismiss();
            }
        });



        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameAcid.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
            }
        });

        final int [] progress ={
                R.id.point1, R.id.point2, R.id.point3,R.id.point4, R.id.point5, R.id.point6,R.id.point7, R.id.point8, R.id.point9,R.id.point10,
        };

        final Animation a = AnimationUtils.loadAnimation(GameAcid.this, R.anim.alpha);


        numLeftBottom = random.nextInt(9);
        img_left_bottom.setImageResource(array.images1[numLeftBottom]);

        numRightBottom = random.nextInt(9);
        while (numLeftBottom==numRightBottom){
            numRightBottom = random.nextInt(9);
        }
        img_right_bottom.setImageResource(array.images1[numRightBottom]);


        img_left_bottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right_bottom.setEnabled(false);
                    if(numLeftBottom>numRightBottom ){
                        img_left_bottom.setImageResource(R.drawable.img_right);
                    }else{
                        img_left_bottom.setImageResource(R.drawable.img_wrong);
                    }

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeftBottom>numRightBottom ){
                        if (count<10){
                            count+=1;
                        }
                        for(int i =0; i<10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i =0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);

                        }
                    }else{
                        if(count>0){
                            count = count - 1;
                        }
                        for(int i =0; i<9; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i =0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==10){
                        dialogEnd.show();

                    }else{
                        numLeftBottom = random.nextInt(9);
                        img_left_bottom.setImageResource(array.images1[numLeftBottom]);
                        img_left_bottom.startAnimation(a);

                        numRightBottom = random.nextInt(9);
                        while (numLeftBottom==numRightBottom){
                            numRightBottom = random.nextInt(9);
                        }
                        img_right_bottom.setImageResource(array.images1[numRightBottom]);
                        img_right_bottom.startAnimation(a);

                        img_right_bottom.setEnabled(true);
                    }

                }
                return true;
            }
        });

        img_right_bottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left_bottom.setEnabled(false);

                    if(numLeftBottom<numRightBottom ){
                        img_right_bottom.setImageResource(R.drawable.img_right);
                    }else{
                        img_right_bottom.setImageResource(R.drawable.img_wrong);
                    }

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeftBottom<numRightBottom ){
                        if (count<10){
                            count+=1;
                        }
                        for(int i =0; i<10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i =0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);

                        }
                    }else{
                        if(count>0){
                            count = count - 1;
                        }
                        for(int i =0; i<9; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i =0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count==10){
                        dialogEnd.show();

                    }else{
                        numLeftBottom = random.nextInt(9);
                        img_left_bottom.setImageResource(array.images1[numLeftBottom]);
                        img_left_bottom.startAnimation(a);

                        numRightBottom = random.nextInt(9);
                        while (numLeftBottom==numRightBottom){
                            numRightBottom = random.nextInt(9);
                        }
                        img_right_bottom.setImageResource(array.images1[numRightBottom]);
                        img_right_bottom.startAnimation(a);

                        img_left_bottom.setEnabled(true);
                    }

                }
                return true;
            }
        });

    }
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(GameAcid.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){

        }
    }
}