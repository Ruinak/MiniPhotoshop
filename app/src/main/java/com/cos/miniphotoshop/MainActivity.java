package com.cos.miniphotoshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    // 위젯 변수 6개와 클래스 변수 1개를 전역변수로 선언
    private ImageButton ibZoomIn, ibZoomOut, ibRotate, ibBright, ibDark, ibGray;
    private MyGraphicView graphicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");

        // activity_main.xml 의 pictureLayout 을 인플레이트 한 후 MyGraphicView 형 클래스 변수를 첨부
        // 결국 아래쪽 레이아웃에는 MyGraphicView 에서 설정한 내용이 출력 됨
        LinearLayoutCompat pictureLayout = findViewById(R.id.pictureLayout);
        graphicView = new MyGraphicView(this);
        pictureLayout.addView(graphicView);
    }

    // MyGraphicView 클래스를 정의
    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }
}