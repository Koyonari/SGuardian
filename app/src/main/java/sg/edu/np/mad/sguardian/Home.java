package sg.edu.np.mad.sguardian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the action bar for a cleaner UI that matches the wireframe
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_home);

        // Initialize profile circle views
        ImageView profile1 = findViewById(R.id.profile1);
        ImageView profile2 = findViewById(R.id.profile2);
        ImageView profile3 = findViewById(R.id.profile3);

        // Initialize switches
        SwitchCompat elaraSwitch = findViewById(R.id.elaraSwitch);
        SwitchCompat stellanSwitch = findViewById(R.id.stellanSwitch);
        SwitchCompat callumSwitch = findViewById(R.id.callumSwitch);

        // Initialize add app button
        Button addAppButton = findViewById(R.id.addAppButton);

        // Initialize remove app buttons
        ImageButton removeYoutubeButton = findViewById(R.id.removeYoutubeButton);
        ImageButton removeGithubButton = findViewById(R.id.removeGithubButton);
        ImageButton removeBrawlStarsButton = findViewById(R.id.removeBrawlStarsButton);
        ImageButton removeNotionButton = findViewById(R.id.removeNotionButton);

        // Set up profile circle click listeners
        View.OnClickListener profileClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String profileName = "";
                if (v.getId() == R.id.profile1) {
                    profileName = "Profile 1";
                } else if (v.getId() == R.id.profile2) {
                    profileName = "Profile 2";
                } else if (v.getId() == R.id.profile3) {
                    profileName = "Profile 3";
                }

                Toast.makeText(Home.this, profileName + " selected", Toast.LENGTH_SHORT).show();
                // Intent could be added here to navigate to profile details
            }
        };

        profile1.setOnClickListener(profileClickListener);
        profile2.setOnClickListener(profileClickListener);
        profile3.setOnClickListener(profileClickListener);

        // Set up switch change listeners
        elaraSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(Home.this, "Elara's monitoring " + status, Toast.LENGTH_SHORT).show();
        });

        stellanSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(Home.this, "Stellan's monitoring " + status, Toast.LENGTH_SHORT).show();
        });

        callumSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(Home.this, "Callum's monitoring " + status, Toast.LENGTH_SHORT).show();
        });

        // Set up add app button click listener
        addAppButton.setOnClickListener(v -> {
            Toast.makeText(Home.this, "Add new app to monitor", Toast.LENGTH_SHORT).show();
            // Intent could be added here to navigate to app selection screen
        });

        // Set up remove app button click listeners
        removeYoutubeButton.setOnClickListener(v -> {
            Toast.makeText(Home.this, "YouTube removed from monitoring", Toast.LENGTH_SHORT).show();
        });

        removeGithubButton.setOnClickListener(v -> {
            Toast.makeText(Home.this, "GitHub removed from monitoring", Toast.LENGTH_SHORT).show();
        });

        removeBrawlStarsButton.setOnClickListener(v -> {
            Toast.makeText(Home.this, "Brawl Stars removed from monitoring", Toast.LENGTH_SHORT).show();
        });

        removeNotionButton.setOnClickListener(v -> {
            Toast.makeText(Home.this, "Notion removed from monitoring", Toast.LENGTH_SHORT).show();
        });
    }
}