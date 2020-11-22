package com.systemsplanet;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static List<Voter> load() {
        List<Voter> allVoters = new ArrayList<>();
        System.out.println("Loading Georgia Absentee Voter Data");
        long begin = System.currentTimeMillis();
        for (int x = 1; x <= 159; x++) { //159
            String file = String.format("%03d.csv", x);
            long start = System.currentTimeMillis();
            List<Voter> voters = FileUploadService.readCSVContentInArray(
                    "https://media.githubusercontent.com/media/systemsplanet/systemsplanet.github.io/master/data/" + file,
                    Voter.class, ',', 1);
            allVoters.addAll(voters);
            long elapsed = System.currentTimeMillis() - start;
            long size = voters.size();
            long recsPerMs = size / elapsed;
            System.out.println(file + " " + String.format("%6d", size) + " records " + String.format("%6d", elapsed) + " milliseconds " + String.format("%5d", recsPerMs) + " recsPerMs " + String.format("%8d", allVoters.size()) + " total");
        }
        System.out.println("load completed in " + ((System.currentTimeMillis() - begin) / 1000) + " seconds");
        return allVoters;
    }
}
