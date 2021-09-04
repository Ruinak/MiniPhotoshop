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
    // 축척으로 사용될 전역변수 2개를 선언
    private static float scaleX = 1, scaleY = 1;
    // 회전 각도로 사용될 전역변수 선언
    private static float angle = 0;

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

        init();
        initLr();
    }

    public void init(){
        ibZoomIn = findViewById(R.id.ibZoomIn);
        ibZoomOut = findViewById(R.id.ibZoomOut);
        ibRotate = findViewById(R.id.ibRotate);
    }

    public void initLr(){
        // 확대 버튼을 클릭할 때마다 축척 전역변수가 0.2씩 증가함
        ibZoomIn.setOnClickListener(v -> {
            scaleX = scaleX + 0.2f;
            scaleY = scaleY + 0.2f;
            // 확대를 위해서 onDraw( ) 메서드를 다시 호출해야하는데
            // 뷰의 invalidate( ) 메서드는 onDraw( )를 자동으로 호출함
            graphicView.invalidate();
        });
        // 확대 버튼을 클릭할 때마다 축척 전역변수가 0.2씩 감소함
        ibZoomOut.setOnClickListener(v -> {
            scaleX = scaleX - 0.2f;
            scaleY = scaleY - 0.2f;
            graphicView.invalidate();
        });
        // 회전하기 버튼을 클릭할 때마다 회전각도가 20도씩 증가함
        ibRotate.setOnClickListener(v -> {
            angle = angle + 20;
            graphicView.invalidate();
        });
    }

    // MyGraphicView 클래스를 정의
    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 화면(뷰)의 중앙을 구하고, 전역변수에 설정된 값으로 캔버스의 축척을 설정
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            // 전역변수에 설정된 각도로 캔버스를 회전시킴
            canvas.rotate(angle, cenX, cenY);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }
}