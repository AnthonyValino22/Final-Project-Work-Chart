import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello, Welcome to Build your Path");
    System.out.println("In order to get started what are you aiming for from the list selected below:\n");

    Scanner userInput = new Scanner(System.in);
    int choice;

    do {
      showMenu();
      System.out.println("Enter your Choice:");
      choice = userInput.nextInt();
      System.out.println();

      switch (choice) {
        case 1:
          bsCyber(userInput); // Pass the userInput Scanner to bsCyber method
          break;
        case 2:
          baComScience(userInput); // Pass the userInput Scanner to baComScience method
          break;
        case 3:
          bsComScience(userInput);
          break;
        case 4:
          baIT(userInput);
          break;
        case 5:
          bsIT(userInput);
          break;
        case 6:
          System.out.println("Quitting...");
          break;
        default:
          System.out.println("Please select a valid option from the list below.");
          break;
      }
    } while (choice != 6);

    userInput.close();
  }

  static void showMenu() {
    System.out.println("1- B.S. in Cybersecurity");
    System.out.println("2- B.A. in Computer Science");
    System.out.println("3- B.S. in Computer Science");
    System.out.println("4- B.A. in Information Technology");
    System.out.println("5- B.S. in Information Technology");
    System.out.println("6- Quit");
  }

  static void bsCyber(Scanner userInput) {
    System.out.println("You selected B.S. in Cybersecurity");
    System.out.println("Please type Fall, Spring, or Summer (case-insensitive):");

    String term;
    while (true) {
      term = userInput.next().toLowerCase();
      userInput.nextLine(); // Consume the newline character in the input buffer

      if (term.equals("fall") || term.equals("spring") || term.equals("summer")) {
        break;
      } else {
        System.out.println("Please select a valid option (Fall, Spring, or Summer).");
      }
    }

    // The rest of your code for B.S. in Cybersecurity after valid input...
    System.out.println("You Selected the " + term + " Term\n");
    System.out.println("What Year is it:");
    int year = userInput.nextInt();
    userInput.nextLine(); // Consume the newline character in the input buffer
    System.out.println("\n");
    System.out.println(
        "The B.S. in Cybersecurity prepares students for a cybersecurity professional career with a primary focus on information security analysis.\n");
    System.out.println("The classes you will be taking for " + term + " " + year + " are:");

    String fileName = "BS Cybersecurity.txt";
    ArrayList<String> coursesList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        coursesList.add(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading the file: " + e.getMessage());
      return;
    }

    Collections.shuffle(coursesList);

    // Organize the courses by term and year
    Map<String, Map<Integer, ArrayList<String>>> termYearCourses = new HashMap<>();
    termYearCourses.put(term, new HashMap<>());
    termYearCourses.get(term).put(year, new ArrayList<>());

    for (String course : coursesList) {
      String[] parts = course.split("–");
      if (parts.length == 2) {
        String subject = parts[0].trim();
        String id = parts[1].trim();

        termYearCourses.get(term).putIfAbsent(year, new ArrayList<>());
        termYearCourses.get(term).get(year).add(subject + " " + id);
      }
    }

    // Output the organized courses by term and year with maximum four classes per
    // term
    int count = 0;
    String[] semesters = { "Fall", "Spring", "Summer" };
    int currentSemesterIndex = -1;
    for (String t : termYearCourses.keySet()) {
      for (int y : termYearCourses.get(t).keySet()) {
        int currentYear = y;
        if (currentSemesterIndex == -1) {
          currentSemesterIndex = findSemesterIndex(t, semesters);
        }

        if (currentSemesterIndex == 1) { // 1 corresponds to Spring in the semesters array
          currentYear++;
        }

        System.out.println("\nSemester: " + t + " " + currentYear);
        System.out.println("You should be taking the following classes:");
        for (String course : termYearCourses.get(t).get(y)) {
          count++;
          System.out.println(count + ". " + course);
          if (count >= 4) {
            System.out.println("----------------------------");
            count = 0;

            // Determine the next semester and year
            currentSemesterIndex = (currentSemesterIndex + 1) % semesters.length;
            if (currentSemesterIndex == 1) { // If the next semester is Spring
              currentYear++;
            }
            System.out.println("Next Semester: " + semesters[currentSemesterIndex] + " " + currentYear);
          }
        }
      }
    }
    System.out.println();
  }

  static void baComScience(Scanner userInput) {
    System.out.println("You selected B.A. in Computer Science");
  System.out.println("Please type Fall, Spring, or Summer (case-insensitive):");

  String term;
  while (true) {
    term = userInput.next().toLowerCase();
    userInput.nextLine(); // Consume the newline character in the input buffer

    if (term.equals("fall") || term.equals("spring") || term.equals("summer")) {
      break;
    } else {
      System.out.println("Please select a valid option (Fall, Spring, or Summer).");
    }
  }

  System.out.println("You Selected the " + term + " Term\n");
  System.out.println("What Year is it:");
  int year = userInput.nextInt();
  userInput.nextLine(); // Consume the newline character in the input buffer
  System.out.println("\n");
  System.out.println("The B.A. in Computer Science program focuses on the theoretical and mathematical foundations of computing.\n");
  System.out.println("The classes you will be taking for " + term + " " + year + " are:");

  String fileName = "BA Computer Science.txt";
  ArrayList<String> coursesList = new ArrayList<>();
  try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
    String line;
    while ((line = br.readLine()) != null) {
      coursesList.add(line);
    }
  } catch (IOException e) {
    System.out.println("An error occurred while reading the file: " + e.getMessage());
    return;
  }

  Collections.shuffle(coursesList);

  int count = 0;
  String[] semesters = { "Fall", "Spring", "Summer" };
  int currentSemesterIndex = findSemesterIndex(term, semesters);
  int currentYear = year;

  System.out.println("\nSemester: " + term + " " + currentYear);
  System.out.println("You should be taking the following classes:");
  for (String course : coursesList) {
    count++;
    System.out.println(count + ". " + course);
    if (count >= 4) {
      System.out.println("----------------------------");
      count = 0;

      // Determine the next semester and year
      currentSemesterIndex = (currentSemesterIndex + 1) % semesters.length;
      if (currentSemesterIndex == 1) { // If the next semester is Spring
        currentYear++;
      }
      System.out.println("Next Semester: " + semesters[currentSemesterIndex] + " " + currentYear);
    }
  }

  System.out.println();
}
  
  static void bsComScience(Scanner userInput) {
   System.out.println("You selected B.S. in Computer Science");
    System.out.println("Please type Fall, Spring, or Summer (case-insensitive):");

    String term;
    while (true) {
      term = userInput.next().toLowerCase();
      userInput.nextLine(); // Consume the newline character in the input buffer

      if (term.equals("fall") || term.equals("spring") || term.equals("summer")) {
        break;
      } else {
        System.out.println("Please select a valid option (Fall, Spring, or Summer).");
      }
    }

    // The rest of your code for B.S. in Cybersecurity after valid input...
    System.out.println("You Selected the " + term + " Term\n");
    System.out.println("What Year is it:");
    int year = userInput.nextInt();
    userInput.nextLine(); // Consume the newline character in the input buffer
    System.out.println("\n");
    System.out.println(
        "The B.A. in Computer Science program focuses on the theoretical and mathematical foundations of computing.\n");
    System.out.println("The classes you will be taking for " + term + " " + year + " are:");

    String fileName = "BS Computer Science.txt";
    ArrayList<String> coursesList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        coursesList.add(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading the file: " + e.getMessage());
      return;
    }

    Collections.shuffle(coursesList);

    // Organize the courses by term and year
    Map<String, Map<Integer, ArrayList<String>>> termYearCourses = new HashMap<>();
    termYearCourses.put(term, new HashMap<>());
    termYearCourses.get(term).put(year, new ArrayList<>());

    for (String course : coursesList) {
      String[] parts = course.split("–");
      if (parts.length == 2) {
        String subject = parts[0].trim();
        String id = parts[1].trim();

        termYearCourses.get(term).putIfAbsent(year, new ArrayList<>());
        termYearCourses.get(term).get(year).add(subject + " " + id);
      }
    }

    // Output the organized courses by term and year with maximum four classes per
    // term
    int count = 0;
    String[] semesters = { "Fall", "Spring", "Summer" };
    int currentSemesterIndex = -1;
    for (String t : termYearCourses.keySet()) {
      for (int y : termYearCourses.get(t).keySet()) {
        int currentYear = y;
        if (currentSemesterIndex == -1) {
          currentSemesterIndex = findSemesterIndex(t, semesters);
        }

        if (currentSemesterIndex == 1) { // 1 corresponds to Spring in the semesters array
          currentYear++;
        }

        System.out.println("\nSemester: " + t + " " + currentYear);
        System.out.println("You should be taking the following classes:");
        for (String course : termYearCourses.get(t).get(y)) {
          count++;
          System.out.println(count + ". " + course);
          if (count >= 4) {
            System.out.println("----------------------------");
            count = 0;

            // Determine the next semester and year
            currentSemesterIndex = (currentSemesterIndex + 1) % semesters.length;
            if (currentSemesterIndex == 1) { // If the next semester is Spring
              currentYear++;
            }
            System.out.println("Next Semester: " + semesters[currentSemesterIndex] + " " + currentYear);
          }
        }
      }
    }
    System.out.println();
  } 

  static void baIT(Scanner userInput){
    System.out.println("You selected B.A. in Information technology");
    System.out.println("Please type Fall, Spring, or Summer (case-insensitive):");

    String term;
    while (true) {
      term = userInput.next().toLowerCase();
      userInput.nextLine(); // Consume the newline character in the input buffer

      if (term.equals("fall") || term.equals("spring") || term.equals("summer")) {
        break;
      } else {
        System.out.println("Please select a valid option (Fall, Spring, or Summer).");
      }
    }

    // The rest of your code for B.S. in Cybersecurity after valid input...
    System.out.println("You Selected the " + term + " Term\n");
    System.out.println("What Year is it:");
    int year = userInput.nextInt();
    userInput.nextLine(); // Consume the newline character in the input buffer
    System.out.println("\n");
    System.out.println(
        "I.T is designed to prepares students with the competencies and business skills essential for a career in information technology.\n");
    System.out.println("The classes you will be taking for " + term + " " + year + " are:");

    String fileName = "BA Info Tech.txt";
    ArrayList<String> coursesList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        coursesList.add(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading the file: " + e.getMessage());
      return;
    }

    Collections.shuffle(coursesList);

    // Organize the courses by term and year
    Map<String, Map<Integer, ArrayList<String>>> termYearCourses = new HashMap<>();
    termYearCourses.put(term, new HashMap<>());
    termYearCourses.get(term).put(year, new ArrayList<>());

    for (String course : coursesList) {
      String[] parts = course.split("–");
      if (parts.length == 2) {
        String subject = parts[0].trim();
        String id = parts[1].trim();

        termYearCourses.get(term).putIfAbsent(year, new ArrayList<>());
        termYearCourses.get(term).get(year).add(subject + " " + id);
      }
    }

    // Output the organized courses by term and year with maximum four classes per
    // term
    int count = 0;
    String[] semesters = { "Fall", "Spring", "Summer" };
    int currentSemesterIndex = -1;
    for (String t : termYearCourses.keySet()) {
      for (int y : termYearCourses.get(t).keySet()) {
        int currentYear = y;
        if (currentSemesterIndex == -1) {
          currentSemesterIndex = findSemesterIndex(t, semesters);
        }

        if (currentSemesterIndex == 1) { // 1 corresponds to Spring in the semesters array
          currentYear++;
        }

        System.out.println("\nSemester: " + t + " " + currentYear);
        System.out.println("You should be taking the following classes:");
        for (String course : termYearCourses.get(t).get(y)) {
          count++;
          System.out.println(count + ". " + course);
          if (count >= 4) {
            System.out.println("----------------------------");
            count = 0;

            // Determine the next semester and year
            currentSemesterIndex = (currentSemesterIndex + 1) % semesters.length;
            if (currentSemesterIndex == 1) { // If the next semester is Spring
              currentYear++;
            }
            System.out.println("Next Semester: " + semesters[currentSemesterIndex] + " " + currentYear);
          }
        }
      }
    }
    System.out.println();
  } 
  

  static void bsIT(Scanner userInput){
    System.out.println("You selected B.S. in Information technology");
    System.out.println("Please type Fall, Spring, or Summer (case-insensitive):");

    String term;
    while (true) {
      term = userInput.next().toLowerCase();
      userInput.nextLine(); // Consume the newline character in the input buffer

      if (term.equals("fall") || term.equals("spring") || term.equals("summer")) {
        break;
      } else {
        System.out.println("Please select a valid option (Fall, Spring, or Summer).");
      }
    }

    // The rest of your code for B.S. in Cybersecurity after valid input...
    System.out.println("You Selected the " + term + " Term\n");
    System.out.println("What Year is it:");
    int year = userInput.nextInt();
    userInput.nextLine(); // Consume the newline character in the input buffer
    System.out.println("\n");
    System.out.println(
        "I.T is designed for the student who wishes to work as a technical support staff or administrator.\n");
    System.out.println("The classes you will be taking for " + term + " " + year + " are:");

    String fileName = "BS Info Tech.txt";
    ArrayList<String> coursesList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        coursesList.add(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading the file: " + e.getMessage());
      return;
    }

    Collections.shuffle(coursesList);

    // Organize the courses by term and year
    Map<String, Map<Integer, ArrayList<String>>> termYearCourses = new HashMap<>();
    termYearCourses.put(term, new HashMap<>());
    termYearCourses.get(term).put(year, new ArrayList<>());

    for (String course : coursesList) {
      String[] parts = course.split("–");
      if (parts.length == 2) {
        String subject = parts[0].trim();
        String id = parts[1].trim();

        termYearCourses.get(term).putIfAbsent(year, new ArrayList<>());
        termYearCourses.get(term).get(year).add(subject + " " + id);
      }
    }

    // Output the organized courses by term and year with maximum four classes per
    // term
    int count = 0;
    String[] semesters = { "Fall", "Spring", "Summer" };
    int currentSemesterIndex = -1;
    for (String t : termYearCourses.keySet()) {
      for (int y : termYearCourses.get(t).keySet()) {
        int currentYear = y;
        if (currentSemesterIndex == -1) {
          currentSemesterIndex = findSemesterIndex(t, semesters);
        }

        if (currentSemesterIndex == 1) { // 1 corresponds to Spring in the semesters array
          currentYear++;
        }

        System.out.println("\nSemester: " + t + " " + currentYear);
        System.out.println("You should be taking the following classes:");
        for (String course : termYearCourses.get(t).get(y)) {
          count++;
          System.out.println(count + ". " + course);
          if (count >= 4) {
            System.out.println("----------------------------");
            count = 0;

            // Determine the next semester and year
            currentSemesterIndex = (currentSemesterIndex + 1) % semesters.length;
            if (currentSemesterIndex == 1) { // If the next semester is Spring
              currentYear++;
            }
            System.out.println("Next Semester: " + semesters[currentSemesterIndex] + " " + currentYear);
          }
        }
      }
    }
    System.out.println();
  }
  

  static int findSemesterIndex(String semester, String[] semesters) {
    for (int i = 0; i < semesters.length; i++) {
      if (semester.equalsIgnoreCase(semesters[i])) {
        return i;
      }
    }
    return -1;
  }
}