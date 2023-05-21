import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {
    public static class CSVFileHelper {
        public List<String> readLinesFromCSVFile(String path) throws IOException {
            Path filePath = Paths.get(path);
            List<String> results = new ArrayList<>();

            try(BufferedReader buffer = Files.newBufferedReader(filePath, Charset.defaultCharset())){
                String line = "";
                while((line = buffer.readLine()) != null){
                    results.add(line);
                }

            }
            return results;
        }

        public void writeLinesToCSVFile(String path, String... lines) throws IOException {
            Path filePath = Paths.get(path);
            try(BufferedWriter buffer =
                        Files.newBufferedWriter(filePath,
                                Charset.defaultCharset(),
                                StandardOpenOption.APPEND)){
                for(String line:lines){
                    buffer.append(line);
                    buffer.newLine();
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String dirPath = "C:\\Users\\5aleda4rf\\IdeaProjects\\students_registration_system\\app\\data\\batch";
        String filePathStr = "C:\\Users\\5aleda4rf\\IdeaProjects\\students_registration_system\\app\\data\\batch\\database.csv";
        Map<String,Long> mp;
        while(true){
            System.out.println("1- Show Students Statistics\n" +
                    "2- Show Batch Statistics\n" +
                    "3- Exit");
            Scanner input = new Scanner(System.in);
            int choice=input.nextInt();
            if(choice==1){
                mp = new HashMap<>();
                List<String> list=new CSVFileHelper().readLinesFromCSVFile(filePathStr);
                for (String std : list) {
                    String[] student = std.split(",");
                    for(int j=2;j<student.length;j++){
                        mp.merge(student[j], 1L, Long::sum);
                    }
                }
                System.out.println("Number of users: "+list.size());
                for (Map.Entry<String,Long> entry : mp.entrySet())
                    System.out.println("Number of students registered in "+entry.getKey()+": "+entry.getValue().toString());
            }
            else if(choice==2){
                mp = new HashMap<>();
                List<String> list=new CSVFileHelper().readLinesFromCSVFile(filePathStr);
                for (String std : list) {
                    String[] student = std.split(",");
                    for(int j=2;j<student.length;j++){
                        mp.merge(student[j], 1L, Long::sum);
                    }
                }
                System.out.println("Number of users: "+list.size());
                for (Map.Entry<String,Long> entry : mp.entrySet())
                    System.out.println("Number of students registered in "+entry.getKey()+": "+entry.getValue().toString());

                File dir = new File(dirPath);
                String[] children = dir.list();
                assert children != null;
                int numOfVerified=0;
                for (String child : children)
                    if (child.contains("verified")) numOfVerified++;
                System.out.println("Number of batch files: "+children.length);
                System.out.println("Number of verified batch files: "+numOfVerified);
            }
            else if(choice==3){
                System.out.println("System closed Successfully.");
                break;
            }
            else{
                System.out.println("This number not valid ... Enter correct number!");
            }
        }
    }
}