package apps.my.spotter.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.AllPermission;

import apps.my.spotter.R;
import apps.my.spotter.activities.fragments.IssueFragment;
import apps.my.spotter.activities.models.Issue;

import static apps.my.spotter.activities.fragments.IssueFragment.listAdapter;

public class Home extends Base {
//countyPicked, issuePicked static variables in Base class...
    TextView emptyList;
    Spinner countySpinner, issueSpinner;
    int countyCurrentSelection, issueCurrentSelection;
    boolean firstTime = true;
   // ListView IssueListView;
   // ArrayAdapter<Issue> IssueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyList = findViewById(R.id.emptyList);
        countySpinner = findViewById(R.id.spinnerCounty);
        issueSpinner = findViewById(R.id.spinnerIssue);

        //checks if value changes in county spinner............
        countyCurrentSelection = countySpinner.getSelectedItemPosition();
        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (countyCurrentSelection != i){
                    firstTime = false;
                    filterIssues(countyCurrentSelection);
                }
                countyCurrentSelection = i;
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        //..........................

        //checks if value changes in issue spinner............
        issueCurrentSelection = issueSpinner.getSelectedItemPosition();
        issueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (issueCurrentSelection != i){
                    firstTime = false;
                    filterIssues(issueCurrentSelection);
                }
                issueCurrentSelection = i;
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

       // initialIssues();
      /*  IssueListView = findViewById(R.id.recentlyAddedList);
        IssueListView.setEmptyView(emptyList);
        IssueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,IssueList);
        IssueListView.setAdapter(IssueAdapter);*/


    }//....................end of onCreate ..................................


    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Issuemate","Home : " + IssueList);

        issueFragment = IssueFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, issueFragment)
                .commit(); // add it to the current activity
        if(IssueList.isEmpty())
            emptyList.setText((getString(R.string.emptyMessageLbl)));
        else emptyList.setText("");
        filterIssues(0);

    }

    //filters issue on county, issue
    private void filterIssues(int currentPosition){
        if (firstTime){
            //do nothing...
        }else {
            int count = 0;
            IssueList.clear();   //clears all issues from issueList
            //gets selected strings for county and issue
            countyPicked = countySpinner.getSelectedItem().toString();
            issuePicked = issueSpinner.getSelectedItem().toString();

            //checks if both county and issue is set to All....if they are displays everything
            //else filters the list
            if(countyPicked.equalsIgnoreCase("ALL") && issuePicked.equalsIgnoreCase("ALL")){
                for (Issue issue : ALLissueList) {
                    IssueList.add(issue);
                    count = count + 1;
                }
            }
            else{   //steps through All issue objects and checks if county and issue are the same, if they are it updates the IssueList
                    //with the newest filtered objects
                for (Issue issue : ALLissueList) {
                    if ((issue.county.equalsIgnoreCase(countyPicked) || countyPicked.equalsIgnoreCase("ALL")) &&
                            (issue.type.equalsIgnoreCase(issuePicked) || issuePicked.equalsIgnoreCase("ALL"))) {
                        IssueList.add(issue);
                        count = count + 1;
                    }
            }


                //IssueList.clear();
                //IssueList.equals(ALLissueList);
                //listAdapter.notifyDataSetChanged(); // refresh adapter
            }
            IssueFragment.listAdapter.notifyDataSetChanged();
            Toast.makeText(this, "SEARCH COMPLETED", Toast.LENGTH_LONG).show();
        }//end of else.........
    }


    public void initialIssues(){
     /*  Issue a = new Issue("Pothole", "Waterford","12 Plymouth Street","Waterford","Info 1",5);
        Issue b = new Issue("Pothole", "Cork","12 Plymouth Street","Waterford","Info 1",5);
        Issue c = new Issue("Pothole", "Waterford","12 Plymouth Street","Waterford","Info 1",5);
     ALLissueList.add(a);
     ALLissueList.add(b);
     ALLissueList.add(c);*/
     //IssueList.add(a);IssueList.add(b);IssueList.add(c);


        /*   Issue d = new Issue("Pothole", "Waterford","12 Plymouth Street","Waterford","Info 1",5,true);
        Issue e = new Issue("Pothole", "Cork","12 Plymouth Street","Waterford","Info 1",5,true);
        Issue f = new Issue("Pothole", "Waterford","12 Plymouth Street","Waterford","Info 1",5,true);
        Issue g = new Issue("Pothole", "Wexford","12 Plymouth Street","Waterford","Info 1",5,true);
        Issue h = new Issue("Pothole", "Waterford","12 Plymouth Street","Waterford","Info 1",5,true);
        Issue i = new Issue("Pothole", "Wexford","12 Plymouth Street","Waterford","Info 1",5,true);
        Issue j = new Issue("Pothole", "Wexford","12 Plymouth Street","Waterford","Info 1",5,true);
*/
       //  /* IssueList.add(d);IssueList.add(e);
       // IssueList.add(f);IssueList.add(g);IssueList.add(h);IssueList.add(i);IssueList.add(j);  */

    }
}

