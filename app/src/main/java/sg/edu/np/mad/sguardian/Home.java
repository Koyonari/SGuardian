package sg.edu.np.mad.sguardian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Profile Images
        ImageView elaraProfile = findViewById(R.id.elaraProfile);
        ImageView stellanProfile = findViewById(R.id.stellanProfile);
        ImageView callumProfile = findViewById(R.id.callumProfile);

        // Buttons
        Button qrCodeLoginButton = findViewById(R.id.qrCodeLoginButton);
        Button childModeButton = findViewById(R.id.childModeButton);
        Button viewDetailedStatsButton = findViewById(R.id.viewDetailedStatsButton);

        // Profile Click Listeners
        View.OnClickListener profileClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ChildProfile.class);
                // You can pass additional data if needed
                // intent.putExtra("PROFILE_NAME", profileName);
                startActivity(intent);
            }
        };

        elaraProfile.setOnClickListener(profileClickListener);
        stellanProfile.setOnClickListener(profileClickListener);
        callumProfile.setOnClickListener(profileClickListener);

        // QR Code Login
        qrCodeLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement QR code scanning activity
                Toast.makeText(Home.this, "QR Code Login", Toast.LENGTH_SHORT).show();
            }
        });

        // Child Mode Activation
        childModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement child mode activation
                Toast.makeText(Home.this, "Child Mode Activation", Toast.LENGTH_SHORT).show();
            }
        });

        // View Detailed Stats
        viewDetailedStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement detailed stats view
                Toast.makeText(Home.this, "Detailed Stats", Toast.LENGTH_SHORT).show();
            }
        });
    }
}