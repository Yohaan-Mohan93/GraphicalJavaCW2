/******************************************************************************/
/*!
\file   ProspectiveStudent.java
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
       ProspectiveStudent
       ProspectiveStudent
       getName
       getGender
       setGender
       getDateOfBirth
       getDateOfBirth
       getPhoneNumber
       setPhoneNumber
       getEmailAddress
       setEmailAddress
       getId
       getCourseAppliedTo
       setCourseAppliedTo
       getOfferStatus
       setOfferStatus
       getFeeType
       getQualificationsType
       setQualificationsType
       toString
    
  Hours spent on this assignment: 
  8 hours
*/
/******************************************************************************/
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import java.util.*;

public class ProspectiveStudent implements Serializable {
    private int id;
    private String name;
    private Calendar dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String emailAddress;
    private long courseAppliedTo;
    private QualificationsType qualifications;
    private FeeType feeType;
    private OfferStatus offer;
    //provided by UOL
    public ProspectiveStudent() {

		id = 636;
		name = "Fred Test";
		gender = Gender.MALE;
		try{
			Date date = new SimpleDateFormat().parse("12/12/1912");
			dateOfBirth = Calendar.getInstance();
			dateOfBirth.setTime(date);
		}
		catch(ParseException e) {
			System.err.println("Invalid date format");
		}
		phoneNumber = "000000000";
		emailAddress = "fredtest@fakeserver.com";
		courseAppliedTo = 000000;
		qualifications = QualificationsType.POTENTIAL;
		feeType = FeeType.HOME;
		offer = OfferStatus.CONDITIONAL;
    }
    
    public ProspectiveStudent(int id, String name, Calendar dateOfBirth, Gender gender, 
    		String phoneNumber, String emailAddress, long courseAppliedTo, QualificationsType qualifications, FeeType feeType, OfferStatus offer)
    {
    	this.id = id;
    	this.name = name;
    	this.gender = gender;
    	this.dateOfBirth = dateOfBirth;
    	this.phoneNumber = phoneNumber;
    	this.emailAddress = emailAddress;
    	this.courseAppliedTo = courseAppliedTo;
    	this.qualifications = qualifications;
    	this.feeType = feeType;
    	this.offer = offer;
    }
    //provided by UOL
    public String getName() {
        return name;
    }
    //provided by UOL
    public Gender getGender() {
        return gender;
    }
    //provided by UOL
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    //provided by UOL
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }
    //provided by UOL
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //provided by UOL
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //provided by UOL
    public String getEmailAddress() {
        return emailAddress;
    }
    //provided by UOL
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    //provided by UOL
    public int getId() {
        return id;
    }
    //provided by UOL
    public long getCourseAppliedTo() {
        return courseAppliedTo;
    }
    //provided by UOL
    public void setCourseAppliedTo(long courseAppliedTo) {
        this.courseAppliedTo = courseAppliedTo;
    }
    //provided by UOL
    public OfferStatus getOfferStatus() {
        return offer;
    }
    //provided by UOL
    public void setOfferStatus(OfferStatus offer) {
        this.offer = offer;
    }
    //provided by UOL
    public FeeType getFeeType() {
        return feeType;
    }
    //provided by UOL
    public QualificationsType getQualificationsType() {
        return qualifications;
    }
    //provided by UOL
    public void setQualificationsType(QualificationsType qualifications) {
        this.qualifications = qualifications;
    }
    //provided by UOL
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id).append(System.lineSeparator());
        sb.append("Name: ").append(name).append(System.lineSeparator());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDob = formatter.format(dateOfBirth.getTime());
        sb.append("Date Of Birth: ").append(formattedDob).append(System.lineSeparator());
        sb.append("Gender: ").append(gender).append(System.lineSeparator());
        sb.append("Phone Number: ").append(phoneNumber).append(System.lineSeparator());
        sb.append("Email Address: ").append(emailAddress).append(System.lineSeparator());
        sb.append("Course Applied To: ").append(courseAppliedTo).append(System.lineSeparator());
        sb.append("Qualifications: ").append(qualifications).append(System.lineSeparator());
        sb.append("Tuition Fee Status: ").append(feeType).append(System.lineSeparator());
        sb.append("Offer Status: ").append(offer).append(System.lineSeparator());

        return sb.toString();
        //StringBuilder is used here because of the need to make a fairly complex String. Strings are immutable, meaning that they cannot be changed once made. While you can concatenate Strings to make a new String, the original String remains in memory, hence the memory can fill up if a lot of String concatenation is done. StringBuilder is mutable, and also has an overloaded append() method, able to append many different types. See the API for more information. https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
    }
}
