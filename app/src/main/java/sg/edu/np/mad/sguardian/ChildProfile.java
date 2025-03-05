package sg.edu.np.mad.sguardian;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChildProfile extends AppCompatActivity {

    private boolean playStoreEnabled = true;
    private boolean youtubeEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_profile);

        // Profile Details
        TextView profileName = findViewById(R.id.profileName);
        TextView profileAge = findViewById(R.id.profileAge);

        // Initialize profile details
        profileName.setText("Elara");
        profileAge.setText("Age 2");

        // Settings Button
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> openSettingsMenu());

        // Monitoring Status Texts
        TextView locationStatus = findViewById(R.id.locationStatus);
        TextView webAppActivityStatus = findViewById(R.id.webAppActivityStatus);
        TextView screenTimeStatus = findViewById(R.id.screenTimeStatus);

        // Control Status Texts
        TextView playStoreStatus = findViewById(R.id.playStoreStatus);
        TextView youtubeStatus = findViewById(R.id.youtubeStatus);

        // Toggle Play Store Access
        playStoreStatus.setOnClickListener(v -> {
            playStoreEnabled = !playStoreEnabled;
            updatePlayStoreStatus(playStoreStatus);
        });

        // Toggle YouTube Access
        youtubeStatus.setOnClickListener(v -> {
            youtubeEnabled = !youtubeEnabled;
            updateYouTubeStatus(youtubeStatus);
        });

        // View Profile Button
        TextView viewProfileButton = findViewById(R.id.viewProfileButton);
        viewProfileButton.setOnClickListener(v -> viewFullProfile());

        // Add Child Button
        TextView addChildButton = findViewById(R.id.addChildButton);
        addChildButton.setOnClickListener(v -> addNewChild());
    }

    private void openSettingsMenu() {
        Toast.makeText(this, "Settings Menu", Toast.LENGTH_SHORT).show();
        // Implement settings menu logic
    }

    private void updatePlayStoreStatus(TextView statusView) {
        if (playStoreEnabled) {
            statusView.setText("On");
            statusView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            statusView.setText("Off");
            statusView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void updateYouTubeStatus(TextView statusView) {
        if (youtubeEnabled) {
            statusView.setText("On");
            statusView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            statusView.setText("Off");
            statusView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void viewFullProfile() {
        Toast.makeText(this, "Viewing Full Profile", Toast.LENGTH_SHORT).show();
        // Implement full profile view logic
    }

    private void addNewChild() {
        Toast.makeText(this, "Add New Child", Toast.LENGTH_SHORT).show();
        // Implement add child logic
    }
}