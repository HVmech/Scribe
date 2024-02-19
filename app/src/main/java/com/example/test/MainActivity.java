package com.example.test;

import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // Used to load the 'test' library on application startup.
    static {
        System.loadLibrary("test");
    }
    private ActivityMainBinding binding;
    private MyView myView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        setListeners();
    }

    /**
     * A native method that is implemented by the 'test' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void init() {
        textView = binding.sampleText;
        textView.setText(stringFromJNI());

        myView = binding.mView;
    }

    private void setListeners() {
      myView.setOnClickListener(v -> {

          LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) myView.getLayoutParams();

          int viewHeight = myView.getBottom();

          // size one dp in pixels
          int oneDp = (int) (1 * getResources().getDisplayMetrics().density);
          int viewHeightInPixels = viewHeight * oneDp;

          textView.setText("Bottom view (dp) was: " + viewHeight +
                  "\nHeight view (pixel) was: " + viewHeightInPixels +
                  "\nNumber pixels in dp: " + oneDp);

          //Set new value for height of myView
          params.height = viewHeightInPixels + 10;
          myView.setLayoutParams(params);
          myView.postInvalidate();

          //I don't remove this code just for testing this feature by HVmech
      });
    }
}