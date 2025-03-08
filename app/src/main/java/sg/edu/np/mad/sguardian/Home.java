package sg.edu.np.mad.sguardian;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {

    // Mock data classes
    public static class ProfileData {
        private String name;
        private boolean monitoringEnabled;

        public ProfileData(String name, boolean monitoringEnabled) {
            this.name = name;
            this.monitoringEnabled = monitoringEnabled;
        }

        public String getName() { return name; }
        public boolean isMonitoringEnabled() { return monitoringEnabled; }
        public void setMonitoringEnabled(boolean enabled) { this.monitoringEnabled = enabled; }
    }

    public static class AppUsageData {
        private String appName;
        private int hoursUsed;

        public AppUsageData(String appName, int hoursUsed) {
            this.appName = appName;
            this.hoursUsed = hoursUsed;
        }

        public String getAppName() { return appName; }
        public int getHoursUsed() { return hoursUsed; }
    }

    public static class UsageStats {
        private float averageHours;
        private float averageMinutes;
        private List<Float> dailyUsage; // Hours per day of the week

        public UsageStats(float averageHours, float averageMinutes, List<Float> dailyUsage) {
            this.averageHours = averageHours;
            this.averageMinutes = averageMinutes;
            this.dailyUsage = dailyUsage;
        }

        public float getAverageHours() { return averageHours; }
        public float getAverageMinutes() { return averageMinutes; }
        public List<Float> getDailyUsage() { return dailyUsage; }
    }

    // Mock data objects
    private List<ProfileData> profileDataList;
    private List<AppUsageData> appUsageDataList;
    private UsageStats usageStats;

    // UI components that will display mock data
    private List<ImageView> profileImageViews;
    private Map<String, SwitchCompat> profileSwitches;
    private TextView averageTimeText;
    private LinearLayout appListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the action bar for a cleaner UI that matches the wireframe
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_home);

        // Initialize mock data
        initializeMockData();

        // Initialize UI components
        initializeUIComponents();

        // Populate UI with mock data
        populateUIWithMockData();

        // Set up the chart
        setupChart();
    }

    private void initializeMockData() {
        // Create mock profile data
        profileDataList = new ArrayList<>();
        profileDataList.add(new ProfileData("Elara", true));
        profileDataList.add(new ProfileData("Stellan", true));
        profileDataList.add(new ProfileData("Callum", true));
        profileDataList.add(new ProfileData("Maya", false));

        // Create mock app usage data
        appUsageDataList = new ArrayList<>();
        appUsageDataList.add(new AppUsageData("Youtube", 23));
        appUsageDataList.add(new AppUsageData("Github", 20));
        appUsageDataList.add(new AppUsageData("Instagram", 15));
        appUsageDataList.add(new AppUsageData("Notion", 8));

        // Create mock usage statistics
        List<Float> dailyUsage = new ArrayList<>(Arrays.asList(6.0f, 3.0f, 6.0f, 2.0f, 3.0f, 8.0f, 5.0f));
        usageStats = new UsageStats(4.0f, 35.0f, dailyUsage);
    }

    private void initializeUIComponents() {
        // Initialize profile image views
        profileImageViews = new ArrayList<>();
        profileImageViews.add(findViewById(R.id.profile1));
        profileImageViews.add(findViewById(R.id.profile2));
        profileImageViews.add(findViewById(R.id.profile3));
        profileImageViews.add(findViewById(R.id.profile4));

        // Initialize switches
        profileSwitches = new HashMap<>();
        profileSwitches.put("Elara", findViewById(R.id.elaraSwitch));
        profileSwitches.put("Stellan", findViewById(R.id.stellanSwitch));
        profileSwitches.put("Callum", findViewById(R.id.callumSwitch));

        // Initialize average time text
        averageTimeText = findViewById(R.id.averageTimeText);

        // Initialize app list layout
        appListLayout = findViewById(R.id.appListLayout);

        // Initialize add app button
        Button addAppButton = findViewById(R.id.addAppButton);

        // Set up add app button click listener
        addAppButton.setOnClickListener(v -> {
            Toast.makeText(Home.this, "Add new app to monitor", Toast.LENGTH_SHORT).show();
            // Intent could be added here to navigate to app selection screen
        });
    }

    private void populateUIWithMockData() {
        // Set up profile circle click listeners
        for (int i = 0; i < profileImageViews.size() && i < profileDataList.size(); i++) {
            final int index = i;
            ImageView profileImageView = profileImageViews.get(i);
            ProfileData profileData = profileDataList.get(i);

            profileImageView.setOnClickListener(v -> {
                Toast.makeText(Home.this, profileData.getName() + " selected", Toast.LENGTH_SHORT).show();
                // Intent could be added here to navigate to profile details
            });
        }

        // Configure switches based on profile data
        for (Map.Entry<String, SwitchCompat> entry : profileSwitches.entrySet()) {
            String profileName = entry.getKey();
            SwitchCompat switchControl = entry.getValue();

            // Find the corresponding profile data
            for (ProfileData profile : profileDataList) {
                if (profile.getName().equals(profileName)) {
                    // Set initial switch state
                    switchControl.setChecked(profile.isMonitoringEnabled());

                    // Set up listener
                    switchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        profile.setMonitoringEnabled(isChecked);
                        String status = isChecked ? "enabled" : "disabled";
                        Toast.makeText(Home.this, profile.getName() + "'s monitoring " + status, Toast.LENGTH_SHORT).show();
                    });

                    break;
                }
            }
        }

        // Set up average time text
        TextView timeIndicatorText = findViewById(R.id.timeText);
        timeIndicatorText.setText(String.format("%dh %dm",
                (int)usageStats.getAverageHours(),
                (int)usageStats.getAverageMinutes()));

        // Set up app usage text views and remove buttons
        if (appListLayout.getChildCount() >= 4 && appUsageDataList.size() >= 4) {
            for (int i = 0; i < 4; i++) {
                LinearLayout appRow = (LinearLayout) appListLayout.getChildAt(i);
                TextView appTextView = (TextView) appRow.findViewById(
                        getResources().getIdentifier("text" + (i + 1), "id", getPackageName())
                );
                ImageButton removeButton = (ImageButton) appRow.findViewById(
                        getResources().getIdentifier("remove" + getAppRemoveButtonId(i), "id", getPackageName())
                );

                AppUsageData appData = appUsageDataList.get(i);
                String htmlText = "<b>" + appData.getAppName() + "</b><br>" + appData.getHoursUsed() + " hours";
                appTextView.setText(Html.fromHtml(htmlText));

                final int index = i;
                removeButton.setOnClickListener(v -> {
                    Toast.makeText(Home.this, appData.getAppName() + " removed from monitoring", Toast.LENGTH_SHORT).show();
                    // Here you would typically update the data model and UI
                });
            }
        }
    }

    private String getAppRemoveButtonId(int index) {
        // This is a helper method to match the button IDs in the layout
        switch (index) {
            case 0: return "YoutubeButton";
            case 1: return "GithubButton";
            case 2: return "BrawlStarsButton";
            case 3: return "NotionButton";
            default: return "";
        }
    }

    private void setupChart() {
        String[] xValues = {"M", "T", "W", "T", "F", "S", "S"};
        BarChart barChart = findViewById(R.id.chartView);
        barChart.getAxisRight().setEnabled(false);

        // Use mock data for chart
        ArrayList<BarEntry> entries = new ArrayList<>();
        List<Float> dailyUsage = usageStats.getDailyUsage();
        for (int i = 0; i < dailyUsage.size(); i++) {
            entries.add(new BarEntry(i, dailyUsage.get(i)));
        }

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(8);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(ContextCompat.getColor(this, R.color.text_white));
        yAxis.setLabelCount(5, true);
        yAxis.setTextColor(ContextCompat.getColor(this, R.color.text_white));
        yAxis.setGranularity(2f);
        yAxis.setDrawGridLines(false);

        // Define the white and blue colors
        final int whiteColor = ContextCompat.getColor(this, R.color.text_white);
        final int blueColor = ContextCompat.getColor(this, R.color.blue_primary_bright);

        BarDataSet dataSet = new BarDataSet(entries, "Days");
        dataSet.setColor(whiteColor);
        dataSet.setValueTextColor(whiteColor);
        dataSet.setValueTextSize(12f);
        dataSet.setDrawValues(false);
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(whiteColor);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineWidth(2f);
        xAxis.setAxisLineColor(whiteColor);

        // Enable highlighting
        barChart.setHighlightPerTapEnabled(true);

        // Keep track of the currently selected bar
        final int[] currentlySelected = {-1};

        // Create a custom highlight listener
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                final int selectedIndex = (int) e.getX();
                currentlySelected[0] = selectedIndex;
                float selectedValue = e.getY();

                // Create a color animation from white to blue for the selected bar
                final int duration = 300; // animation duration in milliseconds

                // Create a list to track colors for each bar
                final List<Integer> colors = new ArrayList<>();
                for (int i = 0; i < entries.size(); i++) {
                    colors.add(i == selectedIndex ? whiteColor : whiteColor); // Start all white
                }
                dataSet.setColors(colors);

                // Create a color ValueAnimator to fade from white to blue
                ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), whiteColor, blueColor);
                colorAnimator.setDuration(duration);
                colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        // Only update if this is still the selected bar
                        if (currentlySelected[0] == selectedIndex) {
                            int animatedColor = (int) animator.getAnimatedValue();
                            colors.set(selectedIndex, animatedColor);
                            dataSet.setColors(colors);
                            barChart.invalidate();
                        }
                    }
                });

                // Create a custom ValueFormatter to only show value for selected bar
                dataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return "";
                    }

                    @Override
                    public String getBarLabel(BarEntry barEntry) {
                        // For bar entries specifically
                        if (barEntry.getX() == selectedIndex) {
                            float val = barEntry.getY();
                            if (val == (int)val) {
                                return String.valueOf((int)val);
                            } else {
                                return String.valueOf(val);
                            }
                        }
                        return "";
                    }
                });

                // Enable value display for all bars (but our formatter will only show the selected one)
                dataSet.setDrawValues(true);

                // Start the color animation
                colorAnimator.start();
            }

            @Override
            public void onNothingSelected() {
                final int previouslySelected = currentlySelected[0];

                // Only animate if there was a selection
                if (previouslySelected != -1) {
                    currentlySelected[0] = -1;

                    // Create a list to track colors for each bar
                    final List<Integer> colors = new ArrayList<>();
                    for (int i = 0; i < entries.size(); i++) {
                        colors.add(i == previouslySelected ? blueColor : whiteColor);
                    }
                    dataSet.setColors(colors);

                    // Create a color ValueAnimator to fade from blue to white
                    ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), blueColor, whiteColor);
                    colorAnimator.setDuration(300);
                    colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            int animatedColor = (int) animator.getAnimatedValue();
                            colors.set(previouslySelected, animatedColor);
                            dataSet.setColors(colors);
                            barChart.invalidate();
                        }
                    });

                    colorAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // Reset all colors to white when animation completes
                            for (int i = 0; i < entries.size(); i++) {
                                colors.set(i, whiteColor);
                            }
                            dataSet.setColors(colors);

                            // Hide all values
                            dataSet.setDrawValues(false);
                            barChart.invalidate();
                        }
                    });

                    // Start the color animation
                    colorAnimator.start();
                } else {
                    // If there was no selection, just reset everything
                    dataSet.resetColors();
                    for (int i = 0; i < entries.size(); i++) {
                        dataSet.setColor(whiteColor);
                    }
                    dataSet.setDrawValues(false);
                    barChart.invalidate();
                }
            }
        });

        // Set touch interaction options
        barChart.setTouchEnabled(true);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);

        barChart.animateY(500);
    }
}