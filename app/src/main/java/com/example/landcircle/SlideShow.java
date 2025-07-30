//package com.example.landcircle;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.os.Handler; // No Looper specified

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

/*public class SlideShow extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Handler handler = new Handler(); // Problem 1: Handler may not be tied to Main Looper
    private Runnable sliderRunnable;
    private int currentPage = 0; // Problem 2: Manual tracking of current page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slide_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_slide_show), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; // Problem 3: All ViewPager logic is inside here

            // This line is unreachable because of the 'return insets;' above it.
            // Problem 4: Unreachable code for ViewPager initialization.
            viewPager = findViewById(R.id.viewPager);

            // Add slides (Replace with your own images and messages)
            List<SlideItem> slides = new ArrayList<>();
            slides.add(new SlideItem(R.drawable.forest, "Welcome to LandCircle - Trusted Land Deals"));
            slides.add(new SlideItem(R.drawable.fresh, "Join a Community of Verified Land Owners"));
            slides.add(new SlideItem(R.drawable.happy, "Transparency & Security in Every Transaction"));

            SlideAdapter adapter = new SlideAdapter(slides);
            viewPager.setAdapter(adapter);

            // Auto-slide every 3 seconds
            sliderRunnable = new Runnable() {
                @Override
                public void run() {
                    if (currentPage == slides.size()) { // Problem 5: Logic for resetting currentPage
                        currentPage = 0;
                    }
                    viewPager.setCurrentItem(currentPage++, true); // Problem 6: Potential issues if user swipes
                    handler.postDelayed(this, 3000);
                }
            };

            handler.postDelayed(sliderRunnable, 3000); // Problem 7: Auto-slide starts without considering lifecycle
        }
    });
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(sliderRunnable); // Problem 8: Only stops in onDestroy
    }
}*/
