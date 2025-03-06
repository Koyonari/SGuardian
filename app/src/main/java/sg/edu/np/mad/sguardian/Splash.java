package sg.edu.np.mad.sguardian;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Hide the action bar for a cleaner UI that matches the wireframe
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_splash);

        // Enable edge-to-edge and handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find views for animation
        ImageView lockIcon = findViewById(R.id.lockIcon);
        TextView appTitle = findViewById(R.id.appTitle);
        TextView appSubtitle = findViewById(R.id.appSubtitle);

        // Scale and fade in animations
        ObjectAnimator scaleXLock = ObjectAnimator.ofFloat(lockIcon, View.SCALE_X, 0.3f, 1f);
        ObjectAnimator scaleYLock = ObjectAnimator.ofFloat(lockIcon, View.SCALE_Y, 0.3f, 1f);
        ObjectAnimator alphaLock = ObjectAnimator.ofFloat(lockIcon, View.ALPHA, 0f, 1f);

        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(appTitle, View.ALPHA, 0f, 1f);
        ObjectAnimator titleTranslateY = ObjectAnimator.ofFloat(appTitle, View.TRANSLATION_Y, 50f, 0f);

        ObjectAnimator subtitleAlpha = ObjectAnimator.ofFloat(appSubtitle, View.ALPHA, 0f, 1f);
        ObjectAnimator subtitleTranslateY = ObjectAnimator.ofFloat(appSubtitle, View.TRANSLATION_Y, 50f, 0f);

        // Animator sets
        AnimatorSet lockAnimSet = new AnimatorSet();
        lockAnimSet.playTogether(scaleXLock, scaleYLock, alphaLock);
        lockAnimSet.setDuration(800);
        lockAnimSet.setInterpolator(new AnticipateOvershootInterpolator());

        AnimatorSet titleAnimSet = new AnimatorSet();
        titleAnimSet.playTogether(titleAlpha, titleTranslateY);
        titleAnimSet.setDuration(600);
        titleAnimSet.setStartDelay(300);
        titleAnimSet.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet subtitleAnimSet = new AnimatorSet();
        subtitleAnimSet.playTogether(subtitleAlpha, subtitleTranslateY);
        subtitleAnimSet.setDuration(600);
        subtitleAnimSet.setStartDelay(500);
        subtitleAnimSet.setInterpolator(new AccelerateDecelerateInterpolator());

        // Combine all animations
        AnimatorSet overallAnimSet = new AnimatorSet();
        overallAnimSet.playSequentially(lockAnimSet, titleAnimSet, subtitleAnimSet);
        overallAnimSet.start();

        // Navigate to Home after animation
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, Home.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 2500);
    }
}