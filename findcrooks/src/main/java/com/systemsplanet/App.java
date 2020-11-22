package com.systemsplanet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Voter> allVoters = Loader.load();
        Collections.sort(allVoters, new Voter.SortbyFnLnMiDob());
        Voter lv = null;
        boolean lastMatch = false;
        try {
            FileWriter myWriter = new FileWriter("../data/duplicates.csv");
            myWriter.write("County,VoterId,LastName,FirstName,MiddleName,Suffix,StreetNum,StreetName,AptUnit,City,State,ZipCode,MailingStreetNum,MailingStreetName,MailingAptUnit,MailingCity,MailingState,MailingZipCode,AppStatus,BallotStatus,StatusReason,AppDate,BallotIssuedDate:date,BallotReturnDate:date,BallotStyle,BallotAssisted,ChallengedProvisional,IDRequired,MunicipalPrecinct,CountyPrecinct,CNG,SEN,HOUSE,JUD,ComboNum,VoteCenterID,BallotID,PostNum,Party\n");
            for (Voter v : allVoters) {
                if (lv == null) { lv=v; continue;}
                if (lv.lastName.equals(v.lastName) &&
                        lv.firstName.equals(v.firstName) &&
                        lv.middleName.equals(v.middleName) &&
                        lv.suffix.equals(v.suffix) &&
                        lv.mailingStreetName.equals(v.mailingStreetName) &&
                        lv.ballotStatus.equals(v.ballotStatus) &&
                        lv.ballotStatus.equals("A") &&
                        !lv.voterId.equals(v.voterId)) {
                    myWriter.write(lv + "\n");
                    lastMatch = true;
                } else if (lastMatch) {
                    myWriter.write(lv + "\n");
                    lastMatch = false;
                }
                lv = v;
            }
            if (lastMatch) {
                myWriter.write(lv + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }
    }
}
