
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class Admissions {
    private ArrayList<ProspectiveStudent> prospectiveStudents;
    private Scanner scanner;
    private boolean finished;

    public static void main(String[] args) {
        new Admissions().run();
    }

    public Admissions() {
        prospectiveStudents = load();
        if (prospectiveStudents == null) prospectiveStudents = load2();
        scanner = new Scanner(System.in);
        finished = false;
    }

    public void run() {
        String input;
        while (!finished) {
            printMainMenu();
            boolean invalidInput = false;
            do {
                input = getUserInput();
                if (isValidMenuChoice(input)) {
                    processMenuChoice(input);
                    invalidInput = false;
                } else {
                    invalidInput(input);
                    invalidInput = true;
                }
            } while (invalidInput);
        }
    }

    private static ArrayList<ProspectiveStudent> load() {
		System.out.println("Loading data...");
		return AdmissionsFileManager.deserialize("student_admissions_file.ser");
    }

    private static ArrayList<ProspectiveStudent> load2() {
		System.out.println("First attempt at loading data failed. Trying again...");
		return AdmissionsFileManager.read();
    }


    private void printMainMenu() {
        System.out.println();
        System.out.println("UNIVERSITY ADMISSIONS SYSTEM");
        System.out.println("Select one of the following options:");
        System.out.println("1) Add prospective student");
        System.out.println("2) Edit prospective student");
        System.out.println("3) Remove prospective student");
        System.out.println("4) View all prospective students");
        System.out.println("5) Exit");
    }

    private void processMenuChoice(String input) {
        int i = Integer.parseInt(input);

        if (i == 1) {
            addProspectiveStudent();
        }

        if (i == 2) {
            editProspectiveStudent();
        }

        if (i == 3) {
            removeProspectiveStudent();
        }

        if (i == 4) {
            showAll();
        }

        if (i == 5) {
            quit();
        }
    }

    private Calendar getDateOfBirthFromUser() {
        Calendar dob = null;
        String dateOfBirth = "";

        while (true) {
            try {
                System.out.print("Enter date of birth (dd/mm/yyyy): ");
                dateOfBirth = getUserInput();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
                dob = Calendar.getInstance();
                dob.setTime(date);
                return dob;
            } catch (ParseException e) {
                System.out.println("Invalid date.");
            }
        }
    }

    private Gender getGenderFromUser() {
        while (true) {
            try {
                System.out.print("Enter gender (male/female/other): ");
                return Gender.valueOf(getUserInput().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Not a recognised gender.");
            }
        }
    }

    private QualificationsType getQualificationsTypeFromUser() {
        while (true) {
            try {
                System.out.print("Enter qualifications type (actual, potential): ");
                return QualificationsType.valueOf(getUserInput().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid qualifications type.");
            }
        }
    }

    private FeeType getFeeTypeFromUser() {
        while (true) {
            try {
                System.out.print("Enter tuition fee rate (home or overseas): ");
                return FeeType.valueOf(getUserInput().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid tuition fee rate.");
            }
        }
    }

    private OfferStatus getOfferStatusFromUser() {
        while (true) {
            try {
                System.out.print("Enter offer made to prospective student (conditional, unconditional, rejected, or pending. defaults to pending if left blank): ");
                String input = getUserInput();
                if (input.isEmpty()) {
                    return OfferStatus.PENDING;
                } else {
                    return OfferStatus.valueOf(input.toUpperCase());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid offer.");
            }
        }
    }

    private String getStringFromUser(String prompt) {
        System.out.print(prompt + ": ");
        return getUserInput();
    }
    private String getNameFromUser() {
        return getStringFromUser("Enter name");
    }

    private String getEmailAddressFromUser() {
        return getStringFromUser("Enter email address");
    }

    private String getPhoneNumberFromUser() {
        return getStringFromUser("Enter phone number");
    }

    private long getCourseCodeFromUser() {
        while (true) {
            try {
                System.out.print("Enter 6 digit course code (eg. 200203): ");
                String input = getUserInput();
                long code = Long.parseLong(input);
                if (input.length() == 6) {
                    return code;
                } else {
                    System.out.println("Course code must be 6 digits long");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    private int getNewestId() {

        int id = -1;

        for(ProspectiveStudent ps : prospectiveStudents) {
            if (ps.getId() > id) {
                id = ps.getId();
            }
        }
        //id now holds the highest currently in use id

        ++id;

        return id;
    }

    private void addProspectiveStudent(int id, String name, Calendar dateOfBirth, Gender gender, String phoneNumber,
                                       String email, long courseCode, QualificationsType qualificationsType,
                                       FeeType feeType, OfferStatus offerStatus) {

        ProspectiveStudent ps = new ProspectiveStudent(id, name, dateOfBirth, gender, phoneNumber, email, courseCode,
                                                       qualificationsType, feeType, offerStatus);
        prospectiveStudents.add(ps);
    }



    private void addProspectiveStudent() {
        String name = getNameFromUser();
        Calendar dob = getDateOfBirthFromUser();
        String email = getEmailAddressFromUser();
        String phoneNumber = getPhoneNumberFromUser();
        Gender gender = getGenderFromUser();
        long courseCode = getCourseCodeFromUser();
        QualificationsType qualificationsType = getQualificationsTypeFromUser();
        FeeType feeType = getFeeTypeFromUser();
        OfferStatus offerStatus = getOfferStatusFromUser();
        int id = getNewestId();

        addProspectiveStudent(id, name, dob, gender, phoneNumber, email, courseCode, qualificationsType, feeType, offerStatus);

        System.out.println("Student named '" + name + "' added to admissions system with ID " + id + ".");
    }


    private boolean askUserQuestion(String question) {
        System.out.print(question + " ");
        String input = getUserInput();
        return input.trim().toLowerCase().startsWith("y");
    }


    private void editProspectiveStudent(ProspectiveStudent ps) {
        System.out.println(ps);
        if (askUserQuestion("Edit email address?")) {
            String email = getEmailAddressFromUser();
            ps.setEmailAddress(email);
            System.out.println("Email address updated");
        }
        if (askUserQuestion("Edit phone number?")) {
            String phone = getPhoneNumberFromUser();
            ps.setPhoneNumber(phone);
            System.out.println("Phone number updated");
        }
        if (askUserQuestion("Edit gender?")) {
            Gender gender = getGenderFromUser();
            ps.setGender(gender);
            System.out.println("Gender updated");
        }
        if (askUserQuestion("Edit course code?")) {
            long code = getCourseCodeFromUser();
            ps.setCourseAppliedTo(code);
            System.out.println("Course code updated");
        }
        if (askUserQuestion("Edit qualifications type?")) {
            QualificationsType qualificationsType = getQualificationsTypeFromUser();
            ps.setQualificationsType(qualificationsType);
            System.out.println("Qualifications type updated");
        }
        if (askUserQuestion("Edit offer status?")) {
            OfferStatus status = getOfferStatusFromUser();
            ps.setOfferStatus(status);
            System.out.println("Offer status updated");
        }
        System.out.println("Editing complete.");
    }

    private void editProspectiveStudent() {
        System.out.print("Enter Prospective Student ID: ");
        String id = getUserInput();
        ProspectiveStudent ps = getProspectiveStudentByID(id);
		if (ps == null) {
			System.out.println("Can't find a prospective student with that ID.");
		} else {
		    editProspectiveStudent(ps);
		}
    }


    private void removeProspectiveStudent() {
        System.out.print("Enter Prospective Student ID: ");
        String id = getUserInput();
        ProspectiveStudent ps = getProspectiveStudentByID(id);
		if (ps == null) {
			System.out.println("Can't find a prospective student with that ID.");
		} else {
            prospectiveStudents.remove(ps);
			System.out.println("Prospective student '" + ps.getName() + "' (ID " + id + ") has been removed.");
		}
    }

    private ProspectiveStudent getProspectiveStudentByID(String id) {
        try {
            int i = Integer.parseInt(id);
            for (ProspectiveStudent ps : prospectiveStudents) {
                if (ps.getId() == i) {
                    return ps;
                }
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void showAll() {
        for(ProspectiveStudent ps : prospectiveStudents) {
            System.out.println(ps);
        }
    }

    private void quit() {
        if (askUserQuestion("Save before exiting?")) {
            save();
            System.out.println("Saved.");
            if (askUserQuestion("Export to text file?")) {
                export();
                System.out.println("Exported.");
            }
        }
        System.out.println("Goodbye.");
        finished = true;
        scanner.close();
    }

    private void save() {
        AdmissionsFileManager.serialize(prospectiveStudents, "student_admissions_file.ser");
    }

    private void export() {
		System.out.print("Enter file name to export to: ");
        String s = getUserInput();
        AdmissionsFileManager.write(prospectiveStudents,s);
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void invalidInput(String input) {
        System.out.println("I don't know what to do with '" + input + "'.");
    }


    private boolean isValidMenuChoice(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i >= 1 && i <= 5) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
