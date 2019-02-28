package apps.my.spotter.activities.models;

import java.io.Serializable;
import java.util.UUID;

public class Issue implements Serializable {

    public String issueId, type, county;
    public String street;
    public String city;
    public boolean mostImportantIssue;
    public int rating;
    public String info;


    public Issue() {}

    public Issue(String type, String county, String street, String city, String info, int rating, boolean mostImportantIssue)
    {
        this.issueId = UUID.randomUUID().toString();
        this.type = type;
        this.county = county;
        this.street = street;
        this.city = city;
        this.rating = rating;
        this.mostImportantIssue = mostImportantIssue;
        this.info = info;
    }

    @Override
    public String toString() {
        return "TYPE: "+type+". County: "+county+". "+ street + ", " + city + ", " + info
                + ", Issue Level:" + rating + ", Very Dangerous =" + mostImportantIssue;
    }
}


