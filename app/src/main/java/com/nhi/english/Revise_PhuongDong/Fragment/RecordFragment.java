//package com.nhi.english.Revise_PhuongDong.Fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.SystemClock;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.Chronometer;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.melnykov.fab.FloatingActionButton;
//import com.nhi.english.R;
//import com.nhi.english.Revise_PhuongDong.Services.RecordingService;
//
//import java.io.File;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class RecordFragment extends Fragment {
//
////    @BindView(R.id.chronometer) Chronometer chronometer;
//    @BindView(R.id.recording_status_txt) TextView recordingStatusTxt;
//    @BindView(R.id.chronometer) Chronometer chronometer;
//    @BindView(R.id.btnRecord) FloatingActionButton recordBtn;
//
//    private boolean mStartRecording = true;
//    private boolean mPauseRecording = true;
//    long timeWhenPaused = 0;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View recordView = inflater.inflate(R.layout.speaking,container,false);
//        ButterKnife.bind(this,recordView);
//        return recordView;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        recordBtn.setColorPressed(getResources().getColor(R.color.blue));
//    }
//
//    @OnClick(R.id.btnRecord)
//    public void recordAudios(){
//        onRecord(mStartRecording);
//        mStartRecording = !mStartRecording;
//    }
//
//    private void onRecord(boolean start) {
//        Intent intent = new Intent(getActivity(), RecordingService.class);
//        if(start){
//            recordBtn.setImageResource(R.drawable.ic_stop_record_24);
//            Toast.makeText(getContext(), "Recording started", Toast.LENGTH_SHORT).show();
//
//            File folder = new File(Environment.getExternalStorageDirectory() + "/MySoundRecord");
//
//            if(!folder.exists()){
//                folder.mkdir();
//            }
//
//            chronometer.setBase(SystemClock.elapsedRealtime());
//            chronometer.start();
//
//            getActivity().startService(intent);
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//
//            recordingStatusTxt.setText("Recording.........");
//        }
//
//        else{
//            recordBtn.setImageResource(R.drawable.ic_keyboard_record_24);
//            chronometer.stop();
//            chronometer.setBase(SystemClock.elapsedRealtime());
//            timeWhenPaused = 0;
//            recordingStatusTxt.setText("Tap the button tho start recording");
//
//            getActivity().stopService(intent);
//        }
//    }
//}
