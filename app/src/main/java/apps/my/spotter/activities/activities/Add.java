package apps.my.spotter.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import apps.my.spotter.R;
import apps.my.spotter.activities.models.Issue;

public class Add extends Base {

    private String roadName, town, info,county, type, countyText, typeText;
    private EditText roadNameText, townText, infoText;
    private RatingBar ratingBar;
    private int issueLevel;
    private boolean mostImportantIssue;
    Spinner countySpinner, issueSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        //  getActionBar().setTitle("FRed");
        countySpinner = findViewById(R.id.spinnerCounty);
        issueSpinner = findViewById(R.id.spinnerIssue);
        roadNameText = findViewById(R.id.addRoadName);
        townText =  findViewById(R.id.addTown);
        // ratingBar =  findViewById(R.id.addRatingBar);
        infoText =  findViewById(R.id.addInfo);

        ratingBar = findViewById(R.id.addRatingBar);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });
    }
    public void addIssue(View v) {
        roadName = roadNameText.getText().toString();
        town = townText.getText().toString();
        info = infoText.getText().toString();
        Float issueLevelFloat = ratingBar.getRating();
        issueLevel = Math.round(issueLevelFloat);
        county = countySpinner.getSelectedItem().toString();
        type = issueSpinner.getSelectedItem().toString();

        if ((roadName.length() > 0) && (town.length() > 0)
                && (issueLevel > 0)) {
            if(issueLevel>7){
                mostImportantIssue = true;
            }else{
                mostImportantIssue = false;
            }
            Issue c = new Issue(type, county, roadName, town, info,
                    issueLevel, mostImportantIssue);

            // Log.v("Issue","Add : " + IssueList);
            totalNumberOfIssues++;      //increase number of issues by 1
            IssueList.add(c);
            ALLissueList.add(c);

            startActivity(new Intent(this, Home.class));
        } else
            Toast.makeText(
                    this,
                    "Please Enter Something for "
                            + "\'Location\', \'Town/City\' and \'Issue Level \'",
                    Toast.LENGTH_SHORT).show();
    }
}
