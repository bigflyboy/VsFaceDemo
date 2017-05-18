package com.visionin.wangzhiyuan.vsfacedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private ImageButton mBtnRevert, mBtnPicture, mBtnVideo, mBtnFair, mBtnFace;
    private ExampleControl mControl;
    public LinearLayout mLineMain, mLineFair;
    private SeekBar mBarFair, mBarDerma, mBarTender;
    private SurfaceView mSurface;
    private static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mControl = new ExampleControl(this);

        mBtnRevert = (ImageButton) findViewById(R.id.btn_revert);
        mBtnPicture = (ImageButton) findViewById(R.id.btn_picture);
        mBtnVideo = (ImageButton) findViewById(R.id.btn_video);
        mBtnFair = (ImageButton) findViewById(R.id.btn_fair);
        mBtnFace = (ImageButton) findViewById(R.id.btn_face);
        mLineMain = (LinearLayout) findViewById(R.id.line_main);
        mLineFair = (LinearLayout) findViewById(R.id.line_fair);

        mBarFair = (SeekBar) findViewById(R.id.seek_fair);
        mBarDerma = (SeekBar) findViewById(R.id.seek_derma);
        mBarTender = (SeekBar) findViewById(R.id.seek_tender);

        mSurface = (SurfaceView) findViewById(R.id.surface);

        mBtnRevert.setOnClickListener(this);
        mBtnPicture.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnFair.setOnClickListener(this);
        mBtnFace.setOnClickListener(this);

        mBarFair.setOnSeekBarChangeListener(this);
        mBarDerma.setOnSeekBarChangeListener(this);
        mBarTender.setOnSeekBarChangeListener(this);

        mSurface.setOnClickListener(this);

        mControl.Init(mSurface);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mControl.Open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mControl.Close();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private boolean isVideoRecord = false;
    private boolean isFair = false;
    private boolean isFace = false;

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.surface:

                break;
            case R.id.btn_revert:  //切换前后摄像头
                Toast.makeText(getApplicationContext(), "切换摄像头", Toast.LENGTH_SHORT).show();
                mControl.Switch();
                break;
            case R.id.btn_picture:  //截图
                Toast.makeText(getApplicationContext(), "截图", Toast.LENGTH_SHORT).show();
                mControl.Snapshot();
                break;
            case R.id.btn_video:  //录像
                Toast.makeText(getApplicationContext(), "录像", Toast.LENGTH_SHORT).show();
                if(isVideoRecord){
                    mBtnVideo.setImageResource(R.mipmap.video);
                    mControl.CaptureOff();
                }else{
                    mBtnVideo.setImageResource(R.mipmap.video_red);
                    mControl.CaptureOn();
                }
                isVideoRecord = !isVideoRecord;
                break;
            case R.id.btn_fair:  //打开美颜界面
                Toast.makeText(getApplicationContext(), "美颜", Toast.LENGTH_SHORT).show();
                if(isFair){
                    mLineFair.setVisibility(View.GONE);
                    mBtnFair.setImageResource(R.mipmap.fair);
                }else{
                    mLineFair.setVisibility(View.VISIBLE);
                    mBtnFair.setImageResource(R.mipmap.fair_red);
                }
                isFair = !isFair;
                break;
            case R.id.btn_face:  //打开关闭人脸识别
                Toast.makeText(getApplicationContext(), "人脸检测", Toast.LENGTH_SHORT).show();
                if(isFace){
                    mBtnFace.setImageResource(R.mipmap.face);
                    mControl.FacerOff();
                }else{
                    mBtnFace.setImageResource(R.mipmap.face_red);
                    mControl.FacerOn();
                }
                isFace = !isFace;
                break;
            default:
                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float index = ((float) progress)/10;
        //Log.e(TAG,index+".....");
        switch (seekBar.getId()){
            case R.id.seek_fair:  //美白
                mControl.Brightening(index);
                break;
            case R.id.seek_derma:  //磨皮
                mControl.Smoothing(index);
                break;
            case R.id.seek_tender:  //锐化
                mControl.Sharpening(index);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
