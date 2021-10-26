package com.swu.zzm.java1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView forGround;
    Bitmap copyBitmap;
    Canvas canvas;
    Paint paint;
    Bitmap orgBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到容器里面的图片视图控件
        forGround = findViewById(R.id.iv_forGround);

        // 将需要操作的图片读取出来
        orgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fr);

        // 创建一个和原始图片相同环境的位图
        copyBitmap = Bitmap.createBitmap(orgBitmap.getWidth(),orgBitmap.getHeight(),orgBitmap.getConfig());

        // 创建一个Canvas 画板
        canvas = new Canvas(copyBitmap);

        // 创建一个画笔
        paint = new Paint();

        // 创建一个矩阵
        Matrix matrix = new Matrix();

        // 画一副图
        canvas.drawBitmap(orgBitmap,matrix,paint);

        // 操作这张图片 同透明色去替换
        forGround.setImageBitmap(orgBitmap);

        // 给前景图片添加touch事件
        forGround.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获取当前事件
                int action = event.getAction();

                // 判断状态
                if (action == MotionEvent.ACTION_DOWN){
                    // 获取触摸点的坐标
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    // 替换x y对应的像素
                    for (int i = -4; i < 4; i++) {
                        for (int j = -4; j < 4; j++) {
                            copyBitmap.setPixel(x + i,y + j,Color.TRANSPARENT);
                        }
                    }
                    forGround.setImageBitmap(copyBitmap);
                }
                return true;
            }
        });
    }
}