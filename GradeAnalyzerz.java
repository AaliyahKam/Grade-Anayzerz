import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GradeAnalyzerz {
    
    public static void main(String[] args) {
        String filename = "prog6.txt";
        
        // Create lists to store student names, grades, averages, and letter grades
        ArrayList<String> names = new ArrayList<>();
        ArrayList<ArrayList<Double>> grades = new ArrayList<>();
        ArrayList<Double> averages = new ArrayList<>();
        ArrayList<Character> letterGrades = new ArrayList<>();
        
        // Read the file and populate the lists
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String name = parts[0] + " " + parts[1];
                names.add(name);
                ArrayList<Double> studentGrades = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    studentGrades.add(Double.parseDouble(parts[i]));
                }
                grades.add(studentGrades);
                double average = Average(studentGrades);
                averages.add(average);
                char letterGrade = LetterGrade(average);
                letterGrades.add(letterGrade);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Sort the students by their averages (in descending order) and get the top 5
        ArrayList<Integer> topIndexes = getTopIndexes(averages, 5);
        ArrayList<String> topNames = new ArrayList<>();
        ArrayList<Character> topLetterGrades = new ArrayList<>();
        for (int index : topIndexes) {
            topNames.add(names.get(index));
            topLetterGrades.add(letterGrades.get(index));
        }
        
        // Print the top 5 students with their names and letter grades
        System.out.println("Top 5 Students:");
        for (int i = 0; i < topNames.size(); i++) {
            System.out.println(topNames.get(i) + ": " + topLetterGrades.get(i));
        }
    }
    
    // Calculate the average of a list of grades
    public static double Average(ArrayList<Double> grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    // Calculate the letter grade corresponding to a given average
    public static char LetterGrade(double average) {
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
    
    // Get the indexes of the top n elements in a list
    public static ArrayList<Integer> getTopIndexes(ArrayList<Double> list, int n) {
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Double> copy = new ArrayList<>(list);
        Collections.sort(copy, Collections.reverseOrder());
        for (int i = 0; i < n && i < copy.size(); i++) {
            indexes.add(list.indexOf(copy.get(i)));
        }
        return indexes;
    }
}
