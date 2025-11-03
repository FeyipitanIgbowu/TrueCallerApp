import java.util.HashMap;
import java.util.Map;

public class Caller {
    private String phoneNumber;
    private boolean isSpam;
    private Map<String, Integer> names;

    public Caller(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.isSpam = false;
        this.names = new HashMap<>();
    }

    public void addName(String name) {
        String caseInsensitiveName = name.toLowerCase();
       if (this.names.containsKey(caseInsensitiveName)) {
           this.names.put(caseInsensitiveName, names.get(caseInsensitiveName) + 1);
       }
       else {
               this.names.put(caseInsensitiveName, 1);
       }
    }

    public String getMostCommonName() {
        String mostCommon = "";
        int highest = 0;

        for (String name : names.keySet()) {
            int count = names.get(name);
            if (count > highest) {
                highest = count;
                mostCommon = name;
            }
        }
        return mostCommon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public void markAsSpam() {
        isSpam = true;
    }

    public int getNameCount(String name){
        return names.getOrDefault(name.toLowerCase(), 0);
    }

    public String toFileString() {
        String fileString = "PhoneNumber: " + phoneNumber + "\nIsSpam: " + isSpam + "\nNames:\n";
        for (String name : names.keySet()) {
            fileString += name + ": " + names.get(name) + "\n";
        }
        return fileString;
    }

    public static Caller fromFileString(String fileString) {
        String[] line = fileString.split("\n");
        Caller caller = new Caller(line[0].split(": ")[1]);
        if (Boolean.parseBoolean(line[1].split(": ")[1])) caller.markAsSpam();

        for (int count = 3; count < line.length; count++) {
            String[] parts = line[count].split(": ");
            for (int counter = 0; counter < Integer.parseInt(parts[1]); counter++) {
                caller.addName(parts[0]);
            }
        }
        return caller;
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}
