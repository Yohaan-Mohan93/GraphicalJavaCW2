/******************************************************************************/
/*!
\file   AdmissionsFileManager.java
\author Yohaan Mohan
\par    email: mohan011\@mymail.sim.edu.sg
\par    Student Number: 160291137
\par    Course: C02220
\par    Coursework Assignment #2
\date   12/03/2018
\brief  
    This file contains the implementation of the following methods for the 
    coursework.
      
    Methods include:
    AdmissionsFileManager
    read
    parseDate
    parseGender
    ParseQualifications
    parseFees
    parseOffer
    parseProspectiveStudent
    write
    deserialize
    serialize
    main
    testRead
    
    
  Hours spent on this assignment: 
  8 hours
*/
/******************************************************************************/
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdmissionsFileManager {

    private static final String filename = "student_admissions_file.txt";
    private static final String serialFilename = "student_admissions_file.ser";
    
    private AdmissionsFileManager()
    {}
    
    /**************************************************************************/
    
    //provided by UOL
    public static ArrayList<ProspectiveStudent> read() {
        Scanner in;
        try {
            in = new Scanner(new FileInputStream(filename), "UTF-8");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find '" + filename + "'");
            return null;
        }

        ArrayList<ProspectiveStudent> list = new ArrayList<>();
        while(in.hasNextLine()) {
            String id = in.nextLine();
            if (id.startsWith("ID: ")) { //from this we can be sure that there are the required nine extra lines following this one
                ArrayList<String> details = new ArrayList<>();
                details.add(id);
                for (int i = 0; i < 9; i++) {
                    details.add(in.nextLine());
                }
                ProspectiveStudent ps = parseProspectiveStudent(details);
                if (ps != null) {
                    list.add(ps);
                }
            }
        }
        in.close();
        return list;
    }

    /**************************************************************************/
    //provided by UOL
    private static Calendar parseDate(String s){
		Calendar dob = null;
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
			dob = Calendar.getInstance();
			dob.setTime(date);
		}
		catch (ParseException e) {
			System.err.println("Invalid date format in " + filename);
			return null;
		}
		return dob;
	}

	/**************************************************************************/
    //Changes Strings to the Gender enum
    private static Gender parseGender(String s)
    {
    	try
    	{
    		return Gender.valueOf(s.toUpperCase());
    	}
    	catch (IllegalArgumentException e) 
    	{
            System.out.println("Not a recognised gender.");
            return null;
        }
    }
    //Changes Strings to Qualifications enum
    private static QualificationsType parseQualifications(String s)
    {
    	try
    	{
    		return QualificationsType.valueOf(s.toUpperCase());
    	}
    	catch (IllegalArgumentException e) 
    	{
            System.out.println("Not a recognised qualification.");
            return null;
        }
    }
    //Changes Strings to Fee enum
    private static FeeType parseFees(String s)
    {
    	try
    	{
    		return FeeType.valueOf(s.toUpperCase());
    	}
    	catch (IllegalArgumentException e) 
    	{
            System.out.println("Not a recognised fee type.");
            return null;
        }
    }
    //Changes Strings to Offer enum
    private static OfferStatus parseOffer(String s)
    {
    	try
    	{
    		return OfferStatus.valueOf(s.toUpperCase());
    	}
    	catch (IllegalArgumentException e) 
    	{
            System.out.println("Not a recognised offer status.");
            return null;
        }
    }
    
    private static ProspectiveStudent parseProspectiveStudent(ArrayList<String> details) {
        ArrayList<String> data = new ArrayList<>();
        int id; 
        String name;
        Calendar dob;
        Gender gender;
        String phone;
        String email;
        long course;
        QualificationsType qualificationsType;
        FeeType feeType;
        OfferStatus offerStatus;
        
        for (String s : details) {
            String[] split = s.split("\\:\\s+", 2);
            data.add(split[1]);
        }
        

        id = Integer.parseInt(data.get(0));
        name = data.get(1);
        dob = parseDate(data.get(2));
        gender = parseGender(data.get(3));
        phone = data.get(4);
        email = data.get(5);
        course = Long.parseLong(data.get(6));
        qualificationsType = parseQualifications(data.get(7));
        feeType = parseFees(data.get(8));
        offerStatus = parseOffer(data.get(9));
        
        return new ProspectiveStudent(id, name, dob, gender, phone, email, course, qualificationsType, feeType, offerStatus);
    }
   
    public static void write(ArrayList<ProspectiveStudent> toSave, String s)
    {
  		try
  		{
  			BufferedWriter writer = new BufferedWriter(new FileWriter(s));
  			for(int i = 0; i < toSave.size(); ++i)
  			{
  				writer.write(toSave.get(i).toString());
  				writer.newLine();
  			}
  			writer.close();
  		}
  		catch(IOException e)
  		{
  			System.out.println(s + " could not be created.");
  		}
  		
    }

    public static ArrayList<ProspectiveStudent> deserialize(String s) 
    {
        try
        {
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(s));
			ArrayList<ProspectiveStudent> list = (ArrayList<ProspectiveStudent>) ois.readObject();
            ois.close();
            
            return list;
        }
        catch(IOException e)
        {
        	System.out.println(s + " was not found");
        	return null;
        }
        catch(ClassNotFoundException e)
        {
        	 System.out.println("Class not found");
        	 return null;
        }
    	
    	
    }

    public static void serialize(ArrayList<ProspectiveStudent> prospectiveStudents, String s) 
    {
    	try
  		{
  			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(s));
  			writer.writeObject(prospectiveStudents);
  			writer.close();
  		}
  		catch(IOException e)
  		{
  			System.out.println(s + " could not be created.");
  		}
    }
    //provided by UOL
    public static void main(String[] args) {
        testRead();
        testWrite();
    	testSerialize();
        testDeserialize();
    }
    //provided by UOL
    private static void testRead() {
		System.out.println("Testing the read() method with default file "+filename);
		System.out.println();
        ArrayList<ProspectiveStudent> students = read();

        for(ProspectiveStudent ps : students) {
            System.out.println(ps);
        }
        System.out.println();
        System.out.println("End of test");
        System.out.println();
        System.out.println("/*****************************************/");
        System.out.println();
    }
    //provided by UOL
    private static void testWrite() {
		System.out.println("Testing the write() method with file name testWrite.txt");
		System.out.println();
        write(getTestData(), "testWrite.txt");
	    System.out.println();
		System.out.println("End of test");
		System.out.println();
		System.out.println("/*****************************************/");
        System.out.println();
    }
    //provided by UOL
    private static void testSerialize() {
		System.out.println("Testing the serialize() method with file name testSer.ser");
		System.out.println();
        serialize(getTestData(), "testSer.ser");
        System.out.println();
		System.out.println("End of test");
		System.out.println();
		System.out.println("/*****************************************/");
        System.out.println();
    }
    //provided by UOL
    private static void testDeserialize() {
		System.out.println("Testing the deserialize() method with file testSer.ser");
		System.out.println();
        ArrayList<ProspectiveStudent> students = deserialize(serialFilename);
        for (ProspectiveStudent p : students) {
            System.out.println(p);
        }
        System.out.println();
		System.out.println("End of test");
        System.out.println();
		System.out.println("/*****************************************/");
        System.out.println();
    }
    //provided by UOL
    private static ArrayList<ProspectiveStudent> getTestData() {
        ProspectiveStudent ps1 = new ProspectiveStudent(1, "Adrian Mole", new GregorianCalendar(1980, 0, 20), Gender.MALE, "1237894560", "mole@example.com", 100211L, QualificationsType.ACTUAL, FeeType.HOME, OfferStatus.UNCONDITIONAL);
        ProspectiveStudent ps2 = new ProspectiveStudent(2, "Some Guy", new GregorianCalendar(1980, 2, 31), Gender.MALE, "1234567890", "guy@example.com", 100302L, QualificationsType.ACTUAL, FeeType.OVERSEAS, OfferStatus.UNCONDITIONAL);
        ProspectiveStudent ps3 = new ProspectiveStudent(3, "Guy Man", new GregorianCalendar(1980, 6, 12), Gender.MALE, "0123456789", "guy@example.fr", 100190L, QualificationsType.POTENTIAL, FeeType.HOME, OfferStatus.PENDING);
        ProspectiveStudent ps4 = new ProspectiveStudent(4, "A Lady", new GregorianCalendar(1980, 4, 9), Gender.FEMALE, "07798765", "al@example.ca", 100404L, QualificationsType.POTENTIAL, FeeType.OVERSEAS, OfferStatus.CONDITIONAL);
        ProspectiveStudent ps5 = new ProspectiveStudent(5, "Jane Doe", new GregorianCalendar(1980, 11, 13), Gender.FEMALE, "+447000000", "jdoe@example.co.uk", 100250L, QualificationsType.ACTUAL, FeeType.HOME, OfferStatus.UNCONDITIONAL);
        ProspectiveStudent ps6 = new ProspectiveStudent(6, "Mx Smith", new GregorianCalendar(1980, 8, 1), Gender.OTHER, "+331234567", "mxsmith@example.fr", 100140L, QualificationsType.ACTUAL, FeeType.HOME, OfferStatus.UNCONDITIONAL);

        ArrayList<ProspectiveStudent> pss = new ArrayList<>();
        pss.add(ps1);
        pss.add(ps2);
        pss.add(ps3);
        pss.add(ps4);
        pss.add(ps5);
        pss.add(ps6);
        return pss;
    }
}
