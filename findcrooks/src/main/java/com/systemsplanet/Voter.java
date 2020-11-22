package com.systemsplanet;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Comparator;
//import lombok.Data;
//import javax.persistence.Column;

//@Data
public class Voter {
    @CsvBindByPosition(position = 0) // ; required = true
    //    @Column(name = "VOTER_COUNTY")
    public String county;
    @CsvBindByPosition(position = 1)
    public String voterId;
    @CsvBindByPosition(position = 2)
    public String lastName;
    @CsvBindByPosition(position = 3)
    public String firstName;
    @CsvBindByPosition(position = 4)
    public String middleName;
    @CsvBindByPosition(position = 5)
    public String suffix;
    @CsvBindByPosition(position = 6)
    public String streetNum;
    @CsvBindByPosition(position = 7)
    public String streetName;
    @CsvBindByPosition(position = 8)
    public String aptUnit;
    @CsvBindByPosition(position = 9)
    public String city;
    @CsvBindByPosition(position = 10)
    public String state;
    @CsvBindByPosition(position = 11)
    public String zipCode;
    @CsvBindByPosition(position = 12)
    public String mailingStreetNum;
    @CsvBindByPosition(position = 13)
    public String mailingStreetName;
    @CsvBindByPosition(position = 14)
    public String mailingAptUnit;
    @CsvBindByPosition(position = 15)
    public String mailingCity;
    @CsvBindByPosition(position = 16)
    public String mailingState;
    @CsvBindByPosition(position = 17)
    public String mailingZipCode;
    @CsvBindByPosition(position = 18)
    public String appStatus;
    @CsvBindByPosition(position = 19)
    public String ballotStatus;
    @CsvBindByPosition(position = 20)
    public String statusReason;
    @CsvBindByPosition(position = 21)
    public String appDate;
    @CsvBindByPosition(position = 22)
    String ballotIssuedDate;
    @CsvBindByPosition(position = 23)
    String ballotReturnDate;
    @CsvBindByPosition(position = 24)
    public String ballotStyle;
    @CsvBindByPosition(position = 25)
    public String ballotAssisted;
    @CsvBindByPosition(position = 26)
    public String challengedProvisional;
    @CsvBindByPosition(position = 27)
    public String isIdRequired;
    @CsvBindByPosition(position = 28)
    public String municipalPrecinct;
    @CsvBindByPosition(position = 29)
    public String countyPrecinct;
    @CsvBindByPosition(position = 30)
    public String cng;
    @CsvBindByPosition(position = 31)
    public String sen;
    @CsvBindByPosition(position = 32)
    public String house;
    @CsvBindByPosition(position = 33)
    public String jud;
    @CsvBindByPosition(position = 34)
    public String comboNum;
    @CsvBindByPosition(position = 35)
    public String voteCenterID;
    @CsvBindByPosition(position = 36)
    public String ballotID;
    @CsvBindByPosition(position = 37)
    public String postNum;
    @CsvBindByPosition(position = 38)
    public String party;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voter voter = (Voter) o;

        return voterId.equals(voter.voterId);
    }

    @Override
    public int hashCode() {
        return voterId.hashCode();
    }

    @Override
    public String toString() {
        return 
                 county + ',' +
                 voterId + ',' +
                 lastName + ',' +
                 firstName + ',' +
                 middleName + ',' +
                 suffix + ',' +
                 streetNum + ',' +
                 streetName + ',' +
                 aptUnit + ',' +
                 city + ',' +
                 state + ',' +
                 zipCode + ',' +
                 mailingStreetNum + ',' +
                 mailingStreetName + ',' +
                 mailingAptUnit + ',' +
                 mailingCity + ',' +
                 mailingState + ',' +
                 mailingZipCode + ',' +
                 appStatus + ',' +
                 ballotStatus + ',' +
                 statusReason + ',' +
                 appDate + ',' +
                 ballotIssuedDate + ',' +
                 ballotReturnDate + ',' +
                 ballotStyle + ',' +
                 ballotAssisted + ',' +
                 challengedProvisional + ',' +
                 isIdRequired + ',' +
                 municipalPrecinct + ',' +
                 countyPrecinct + ',' +
                 cng + ',' +
                 sen + ',' +
                 house + ',' +
                 jud + ',' +
                 comboNum + ',' +
                 voteCenterID + ',' +
                 ballotID + ',' +
                 postNum + ',' +
                 party
                ;
    }

    public static class SortbyFnLnMiDob implements Comparator<Voter>
    {
        @Override
        public int compare(Voter o1, Voter o2) {
            int ln = o1.lastName.compareTo(o2.lastName);
            if (ln!=0) return ln;
            int fn = o1.firstName.compareTo(o2.firstName);
            if (fn!=0) return fn;
            int mn = o1.middleName.compareTo(o2.middleName);
            return mn;
        }
    }
}
