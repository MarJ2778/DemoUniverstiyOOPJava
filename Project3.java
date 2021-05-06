/*
/ * Project 3
 * Marian Jacklich
 * See commented code for notes. Working and testing.  See upload file copy for actual submit.
 */
import java.util.*;
import java.io.*;
import java.text.*;

public class Project3 {

	public static void main(String[] args) {
		String userOption;
		University univ = new University();
		Menu m = new Menu();
		
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("\t\t\t Welcome to my Personal Management Program");
		System.out.println("");
		System.out.println("Choose one of the options:");
		System.out.println("");
		
		try {
			do { 
				userOption = m.displayMenu(scnr);
						
				switch (userOption) {
		
				case "1" : 	univ.addFaculty();	
						break;
	
				case "2" : univ.addStudent(); 
						break;
	
				case "3" :  univ.getStudentInvoice();
						break;
	
				case "4" :  univ.getFacultyInfo(); 
						break;
	
				case "5" : univ.addStaff();
						break;
	
				case "6" : univ.getStaffInfo();
						break;
		
				case "7": univ.displayReport(scnr);
						break;
						
				default : System.out.println("\n\nInvalid entry - please try again.");
				} //end switch
		
		} while (!userOption.equals("7"));  //end while
		}//end try	
		catch(Exception ex) {
			System.out.println("Invalid Entry Exception. Please enter valid information." + ex);
			
		}//end catch safety net
		scnr.close();

	}//end main
	
}//end class Project3
	
//----------------Other classes and methods are below______________

class Menu {
	private String option;
	
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}


	public Menu() {
	}

	public String displayMenu(Scanner scan) {
		
		System.out.println("1- Enter the information of a faculty");
		System.out.println("2- Enter the information of a student");
		System.out.println("3- Print tuition invoice for a student");
		System.out.println("4- Print faculty information");
		System.out.println("5- Enter the information of a staff member");
		System.out.println("6- Print the information of a staff member");
		System.out.println("7- Exit Program");
		System.out.println("");
		System.out.print("\t\tEnter your selection: ");
		
		option = scan.nextLine(); 
		
		System.out.println("");
			
		return option;
	}//end method displayMenu	

}//end class Menu

//------------------------------------------------------------------------	


class University {

	private int arraySize = 100;
	
	Person[] stuAndEmploy;  //Both students and employees are in the Person array required for the project
		
	Scanner scan = new Scanner(System.in);  //ignore close warning, closed in main
	
	//initialize all the elements to null first when created
	public University() {
		stuAndEmploy = new Person[arraySize];  
		for (int i = 0; i < arraySize; i++) {
			stuAndEmploy[i] = null;
		}
	}//end default constructor University

	//printAll is only good if you really want to display the entire array
	//Declares one type Person p to go through all of stuAndEmploy array. This will return the "toString" methods for all array data one by one
	public void printAll() {
	for (Person p : stuAndEmploy) {    
			if (p != null)
				System.out.println(p); 

		}
	}//end method printAll

	public void addStudent() throws Exception {
	int i = getIndex();	//walk the array to find the first null
	stuAndEmploy[i] = new Student(); //create a default value instance of Student in the null spot
	boolean isSetOk = false;
	try {
			System.out.println("Enter the student info:");
			System.out.println("");	
				
			System.out.print("\t\tName of Student: ");
			((Student)stuAndEmploy[i]).setName(scan.nextLine());
			System.out.println("");	
			
			do {
			System.out.print("\t\tID: ");
			isSetOk = ((Student)stuAndEmploy[i]).setId(scan.nextLine());
			System.out.println("");
			} while (isSetOk != true);
			
		
			System.out.print("\t\tGpa: ");
			((Student) stuAndEmploy[i]).setGpa(scan.nextDouble());
			System.out.println("");
			System.out.print("\t\tCredit hours: ");
			((Student) stuAndEmploy[i]).setCreditHr(scan.nextDouble());
			scan.nextLine(); //to pick up the hidden carriage return				
					
			System.out.println("\nStudent added!\n");
	}//end try
		catch(Exception ex) {
		System.out.println("Sorry, invalid entry. Please enter correctly:" + ex);
		scan.nextLine();//picks up a stray carriage return if one
	}//end catch safety net for any entries that throw an exception
			
}//end addStudent method


 public void addFaculty() throws Exception {
	String tempRank = "";
	String tempDept = "";
	boolean okSet = false;
	int i = getIndex();	//walk the array to find the first null
	stuAndEmploy[i] = new Faculty(); //create an object of Faculty 
	
	try {
			System.out.println("Enter the Faculty info:");
			System.out.println("");	
				
			System.out.print("\t\tName of the Faculty: ");
			((Faculty)stuAndEmploy[i]).setName(scan.nextLine());
			System.out.println("");	
			
			
			do {
			System.out.print("\t\tID: ");
			okSet = ((Faculty)stuAndEmploy[i]).setId(scan.nextLine());
			System.out.println("");
			} while (okSet != true);
			
			okSet = false;  //reset okSet for the next entry
			
			//method checks for authorized rank and sets it if an authorized rank
			do {
			System.out.print("\t\tRank: ");
			tempRank = scan.nextLine();
			((Faculty)(Employee)stuAndEmploy[i]).setRank(tempRank);  
			okSet = ((Faculty)(Employee)stuAndEmploy[i]).checkRank(tempRank);
			} while (okSet == false);
			
			System.out.println("");
			okSet = false; //reset the set value checker
			
			//method checks for authorized dept and sets it if an authorized dept
			do {
			System.out.print("\t\tDepartment: ");
			tempDept = scan.nextLine();
			((Faculty)(Employee)stuAndEmploy[i]).setDepartment(tempDept);  
			okSet = ((Faculty)(Employee)stuAndEmploy[i]).checkDepartment(tempDept);
			} while (okSet == false);
			
			System.out.println("");
			
			System.out.println("Faculty added!\n");
	}//end try
	catch(Exception ex) {
		System.out.println("\nInvalid entry exception: " + ex);
		
	}//end catch safety net for any entries that throw an exception
						
}//end addFaculty method
  
 public void addStaff() throws Exception {
	String tempStatus = "";
	String tempDept = "";
	boolean okSet = false;
	int i = getIndex();	//walk the array to find the first null
	stuAndEmploy[i] = new Staff(); //create an object of Faculty 
	
		try {
			//System.out.println("Enter the Staff info:"); I personally prefer this project does not have
			//System.out.println("");	
				
			System.out.print("\t\tName of the staff member: ");
			((Staff)stuAndEmploy[i]).setName(scan.nextLine());
			System.out.println("");	
			
			do {
			System.out.print("\t\tEnter the id: ");
			okSet = ((Staff)stuAndEmploy[i]).setId(scan.nextLine());
			System.out.println("");
			} while (okSet != true);
			
			okSet = false; //reset okSet for the next entry
				//method checks for authorized dept and sets it if an authorized dept		
			do {
				System.out.print("\t\tDepartment: ");
				tempDept = scan.nextLine();
				((Staff)(Employee)stuAndEmploy[i]).setDepartment(tempDept);  
				okSet = ((Staff)(Employee)stuAndEmploy[i]).checkDepartment(tempDept);
			} while (okSet == false);
				System.out.println("");
			
				//method checks for authorized status and sets it to Full or Part Time
				okSet = false; //reset the set value checker
			
				do {
					System.out.print("\t\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
					tempStatus = scan.nextLine();
					((Staff)(Employee)stuAndEmploy[i]).setStatus(tempStatus);
					okSet = ((Staff)(Employee)stuAndEmploy[i]).checkStatus(tempStatus);
					} while (okSet == false);
					System.out.println("");
		}//end try
		catch(Exception ex) {
			System.out.println("\nInvalid entry exception: " + ex);
			
		}//end catch safety net for any entries that throw an exception	
			
			System.out.println("");
			System.out.println("Staff member added!\n");
			
 }//end addStaff method


//This method searches the array for the student ID, and calls the invoice from the Student class as required for project2
public void getStudentInvoice() throws Exception {
	String userId = "none";
	boolean found = false;
	
	try {
	System.out.print("Enter the student's id: ");
	userId = scan.nextLine();
	
	for (Person p : stuAndEmploy) {
		if ( p != null)
			if (p.getCodeObType()== 2) {
				if (p.getId().equalsIgnoreCase(userId)){
					found = true;	
					p.print();
				}//if id match
			}// if class match
	}//for each person in studAndEmploy array
	
	if (found != true) {
		System.out.println("No student matched!\n");
	}
	}//end try
	catch(Exception ex) {
		System.out.println("\nInvalid entry exception: " + ex);
		
	}//end catch safety net for any entries that throw an exception	
		
}//end getStudentInvoice method


 public void getFacultyInfo() throws Exception {
	String userId = "none";
	boolean found = false;
	
	try {
	System.out.print("Enter the Faculty's id: ");
	userId = scan.nextLine();
	
		for (Person p : stuAndEmploy) {
			if ( p != null)
				if (p.getCodeObType()== 4) {
					if (p.getId().equalsIgnoreCase(userId)){
					found = true;	
					p.print();
				}//if id match
			}// if class match
		}//for each person in studAndEmploy array
	
		if (found != true) {
		System.out.println("No Faculty matched!\n");
		}
	}//end try
	catch(Exception ex) {
		System.out.println("\nInvalid entry exception: " + ex);
		
	}//end catch safety net for any entries that throw an exception	
		
}//end getFacultyInfo method

 public void getStaffInfo() throws Exception {
	String userId = "none";
	boolean found = false;
	
	try {
	 System.out.print("Enter the Staff's id: ");
	 userId = scan.nextLine();
	
	 for (Person p : stuAndEmploy) {
		if ( p != null)
			if (p.getCodeObType()== 5) {
				if (p.getId().equalsIgnoreCase(userId)){
					found = true;	
					p.print();
				}//if id match
			}// if class match
	}//for each person in studAndEmploy array
	 
	 if (found != true) {
			System.out.println("No Staff member matched!\n");
	 }//end if not found
	 
	}//end try block
		
	catch(Exception ex) {
			System.out.println("\nInvalid entry exception: " + ex);
		}//end catch safety net for any entries that throw an exception	
	
		
}//end getStaffInfo method
 		

//handy walk the array to add an employee or student. Nothing is entered or passed, so no exception handling is used
 private int getIndex() {
	int index = 0;
	for (; index < arraySize; index++) {
		if (stuAndEmploy[index] == null)
		return index;
	}//end for loop
	
	return -1;  // -1 if everything is full
	
	}//end getIndex method

	public void displayReport(Scanner scan) {
		String answer;
		System.out.print("Would you like to create the report? (Y/N): ");
		answer = scan.next();
		if (answer.equalsIgnoreCase("y")){
			try {
				Report r = new Report();
				r.writeReport(stuAndEmploy);  //This method will display confirmation the report was created if requested
			}//end try

			catch(Exception excpt) {
			System.out.println("General error: " + excpt);
			}//catch all for the report
		}//end if
	System.out.println("\nGoodbye!");
	}//end display report

}//end class University
		
//-----------------------------------------------------------------------------------------------

/*Super for both Employee and Student*/
 abstract class Person {
	
	private String name;
	private String id = "LL1234"; //default value if not set by user
	private int codeObType; //object type code
	private boolean idSetOk = false;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	
	//Project 3 setID is changed to a method for a specific type of ID format. Returns true if the ID passed the checks and is set
	public boolean setId(String userID) throws Exception {
		idSetOk = checkId(userID);
		//System.out.println("idSetOk returned is" + idSetOk); diagnostic if needed
		return idSetOk;
		
	}
	public int getCodeObType() {
		return codeObType;
	}
	public void setCodeObType(int codeObType) {
		this.codeObType = codeObType;
	}
	
	//default constructor
	public Person() {
		name = "No name";
		id = "No ID";
		codeObType = 1;
	}
	
	//constructor with parameters passed
	public Person(String name, String id) {
		this.name = name;
		this.id = id;
		codeObType = 1;
	}
	
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + ", codeObType=" + codeObType + "]";
	}
	
	//subclasses are required to have this method, signature line
	public abstract void print();
	
	
	//this method requires the ID to be entered from the keyboard, the boolean will return true if the ID was a valid one and sets it here
	public boolean checkId(String userID) throws IdException{	
	boolean setIdOk = false;
	int userDigitCount = 0;
	
	try {
			userDigitCount = findDigitCount(userID);
				
			if (((userID.length()) != 6) || (userDigitCount != 4)) {
					throw new IdException("\n\t\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit");
			}//end if conditions to throw an invalid entry with 
			
			//if the code gets this far, the if conditions above have been met and the ID can be set
			this.id = userID;
			setIdOk = true;
				
	}//end try
				
	catch(IdException excpt) {
		//System.out.println("Catch Block");
		System.out.println(excpt);
	}//end catch
						
		return setIdOk;
			
	}//end method checkID	


//counts Digit values from a string
	public int findDigitCount(String userString) {	
		String userDigits = userString;
		int count = 0;
		userDigits = userDigits.replaceAll("[^\\d]","");  //[] means one of these [^..] means not one of these \escape \d digit
		//System.out.println("userDigits: " + userDigits); diagnostic if needed
		count = userDigits.length();
		//System.out.println("userDigits length: " + count); diagnostic if needed
	return count;
	}//end method findDigitCount

}//end abstract class Person

 //-------------------------------------------------------------------------------------------------
 
 /*Project 3 requires a user-defined exception, this is used when IDs are set in Person*/

//User-defined exception to display a specific message
 class IdException extends Exception {
	
	protected String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String str1) {
		this.message = str1;
	}

	//Pass a custom message when calling the constructor
	public IdException(String str2){
		message = str2;
	}
	
	public String toString() {
		return (message);
	}

}//end class IdException
 
 //--------------------------------------------------------------------------------------------------
 
 class Student extends Person {

		private double gpa;
		private double creditHr;
		
	    Scanner scnr = new Scanner(System.in);	
		
		

	public double getGpa() {
			return gpa;
		}

		public void setGpa(double gpa) {
			this.gpa = gpa;
		}

	public double getCreditHr() {
			return creditHr;
		}

		public void setCreditHr(double creditHr) {
			this.creditHr = creditHr;
		}
		
	//default constructor, values are null and -1 to identify fields without information provided
	public Student() {  
		super();
		this.gpa = -1.0;
		this.creditHr = -1.0;
		this.setCodeObType(2);
		
	}
		
	//constructor with parameters passed,used in addStudent method
	public Student(String name, String id, double stuGpa, double stuCreditHr) {
			super(name, id);
			this.gpa = stuGpa;
			this.creditHr = stuCreditHr;
			this.setCodeObType(2);
		}


		@Override
	public String toString() {
		return "Student gpa=" + gpa + ", creditHr=" + creditHr + ", getName()=" + getName()
				+ ", getId()=" + getId() + ", getCodeObType()=" + getCodeObType() + ", " + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

		@Override
		public void print() {
		
		double fees = 52.0, payment = 0.00, discount=0.0, rate = 236.45 ; //can change the credit hour rate here
		double discountRate = .25;
			
				System.out.println("");
				System.out.println("\t\tHere is the tuition invoice for " + this.getName() + " : ");
				System.out.println("\t\t--------------------------------------------------------------------------");
				System.out.println("");
		
				System.out.println("\t\t" + this.getName() + "\t\t" + this.getId());
				System.out.println("");				
				System.out.println("\t\tCredit Hours: " + creditHr + "  ($" + rate + "/credit hour)");
				System.out.println("");				
				System.out.println("\t\tFees: $" + (int)fees);
				
				payment = (creditHr * rate) + fees;
				
				//discount if student has a high gpa
				if (gpa >= 3.85) { discount = (payment * discountRate);
				discount = Math.round(discount * 100);
				discount = discount/100;}
				
				
				payment = payment - discount;
				payment = Math.round(payment * 100);
				payment = payment/100;
				//if (payment <= fees) {payment = 0.00;} // if they have no credit hours, removing the fees - commented only to match project requirement
				System.out.println("");				
				System.out.print("\t\tTotal payment (after discount): $" + payment + "\t\t");
				System.out.print("($" + discount + " discount applied)");
				System.out.println("\n");
				System.out.print("\t\t---------------------------------------------------------------------------");	
				System.out.println("");
				System.out.println("");
					
		}//end method print (TuitionInvoice)
				
	}//end class Student
 
 //-----------------------------------------------------------------------------------------------------------------------
 
/*This is a super class to Faculty and Staff*/

abstract class Employee extends Person {
	private String department;

	public String getDepartment() {
		return department; //returns as an string inside the object type
	}

	
	public void setDepartment(String department) {
		this.department = department;
	}//end setDepartment
	
	
	
	public Employee() {
		super();
		department = "no department";
		this.setCodeObType(3);
		
	}

	public Employee(String name, String id, String department) {
		super(name, id);
		this.department = department;
	}

	

	@Override
	public String toString() {
		return "Employee department=" + department + ", getName()=" + getName() + ", getId()=" + getId()
				+ ", getCodeObType()=" + getCodeObType() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass();
	}


	@Override
	public void print() {
		System.out.println("Employee Name: " + super.getName() + "Employee ID: " + super.getId() );
		
		if (department.equalsIgnoreCase("null")) {
			System.out.println("You need to set a department before I can display it");
			}
		else { 
			
			System.out.println(department + " Department");	
		}
	}//end print method
	
	
	//checks department and returns a true or false value if it meets criteria
	public boolean checkDepartment(String department) {
		boolean okSet = false;
		
		if (department.equalsIgnoreCase("mathematics") || department.equalsIgnoreCase("engineering") || department.equalsIgnoreCase("sciences"))
		{	
			department = department.toLowerCase();
			department = department.substring(0,1).toUpperCase() + department.substring(1);
			
			this.department = department;
			okSet = true;
		}
		
		else { 
			System.out.println("\t\tAllowed department: Mathematics, Engineering, or Sciences only");}
		
		return okSet;
			
	}//end checkDepartment method

}//end abstract class Employee

//--------------------------------------------------------------------------------------------------------------------

 class Faculty extends Employee {
	private String rank;
		
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

//default constructor
	public Faculty() {
		this.setName("none");
		this.setDepartment("none");
		this.rank = "none";
		this.setCodeObType(4);
	}

//constructor with parameters passed
	public Faculty(String name, String id, String department, String rank) {
		super(name, id, department);
		checkRank(rank);
		
		this.setCodeObType(4);
	}


	@Override
	public String toString() {
		return "Faculty rank=" + rank + ", getDepartment()=" + getDepartment() + ", \n" + super.toString()
				+ ", getName()=" + getName() + ", getId()=" + getId() + ", \ngetCodeObType()=" + getCodeObType()
				+ ", getClass()=" + getClass();
	}


	@Override
	public void print() {	
		
		System.out.println("\t\t----------------------------------------------------------------------");
		System.out.println("");
		System.out.println("\t\t" + this.getName() + "\t\t" + this.getId());
		System.out.println("");
		System.out.println("\t\t" + this.getDepartment() +" Department," + " " + this.rank);
		System.out.println("");
		System.out.print("\t\t----------------------------------------------------------------------");	
		System.out.println("");
		//scan.nextLine(); //get rid of hidden carriage return
				
	}//end print method
			
	//checks rank and returns a true or false value if it meets criteria
	public boolean checkRank(String rank) {	
	boolean okSet = false;
		
			if (rank.equalsIgnoreCase("professor") || rank.equalsIgnoreCase("adjunct")){ 
				rank = rank.toLowerCase();
				rank = rank.substring(0,1).toUpperCase() + rank.substring(1);
				this.rank = rank;
				okSet = true;
				
				}
			else {
				System.out.println("\t\t" + "\"" + rank + "\"" + " is invalid \n");
				//System.out.println("\t\tPlease use Professor or Adjunct"); //if later desired, this can be used
			}
			return okSet;
	
	}//end checkRank method	
 }//end class Faculty
 
 //---------------------------------------------------------------------------------------------------------
 
class Staff extends Employee {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//default constructor
		public Staff() {
			this.setName("none");
			this.setDepartment("none");
			this.status = "none";
			this.setCodeObType(5);
		}

	//constructor with parameters passed
		public Staff (String name, String id, String department, String status) {
			super(name, id, department);
			checkStatus(status);
			this.setCodeObType(5);
		}

	@Override
	public String toString() {
		return "Staff status=" + status + ", getDepartment()=" + getDepartment() + ", toString()=" + super.toString()
				+ ", getName()=" + getName() + ", getId()=" + getId() + ", getCodeObType()=" + getCodeObType()
				+ ", getClass()=" + getClass();
	}
	
	@Override
	public void print() {	
		
		System.out.println("\t\t----------------------------------------------------------------------");
		System.out.println("");
		System.out.println("\t\t" + this.getName() + "\t\t" + this.getId());
		System.out.println("");
		System.out.println("\t\t" + this.getDepartment() +" Department," + " " + this.status);
		System.out.println("");
		System.out.print("\t\t----------------------------------------------------------------------");	
		System.out.println("");
		//scan.nextLine(); //get rid of hidden carriage return
				
	}//end print method
		
	
	//checks Staff status, returns a true or false value and sets it if it meets criteria,
	public boolean checkStatus(String status) {
	String tempStatus = "none";
	boolean okSet = false;

				//System.out.println("You are in checkStatus. Received: " + status);

				
				if (status.equalsIgnoreCase("p")) {
					tempStatus = "Part Time";
					this.setStatus(tempStatus);
					okSet = true;
					//System.out.println("status now set to: "+ this.getStatus());
				}
				else if (status.equalsIgnoreCase("f")) {
					tempStatus = "Full Time";
					this.setStatus(tempStatus);
					okSet = true;
					//System.out.println("status now set to: "+ this.getStatus());			
				} //end status comparison
				
				else {
				System.out.println("\t\t" + "\"" + status + "\"" + " is invalid \n");
				}
			
				return okSet;
		
	}//end checkStatus method	

}//end class Staff

//-----------------------------------------------------------------------------------------------------

//This class will send a report.dat file to the same location as the program is run. 
//It outputs all of the data entered by the object type. Formatting is per the project 3 specification.
class Report {
	
	private FileOutputStream fileOutStream = null;
	private PrintWriter outFS = null;
	private  String head = "\t\t Report created on ";
	private int countF = 0, countStaff = 0, countStu = 0;
	private double gpa = 0.0, creditHr = 0;
	
	public void writeReport(Person[] stuAndEmploy) throws Exception {
		
		//if an IOException is thrown, the calling method will need to output an error 
		  try {
			  
			 fileOutStream = new FileOutputStream("report.dat"); //opens and creates the file
			 outFS = new PrintWriter(fileOutStream);  //loads the file into the writer method 
		  }
		  
		  catch(IOException ex) {
			  System.out.println("File cannot be created: " + ex);
			  return;  //if the file cannot be created, it will go back to the location called
		  }

		  //arriving here the file can be now written, outputs header and date
		  outFS.println(head +  getDate());
		  outFS.println("\t\t***********************************");
		  outFS.println("\n");
		  
		  outFS.println("   Faculty members");
		  outFS.println("   ----------------------");
			for (int i=0; i<stuAndEmploy.length; i++) {
				if ( stuAndEmploy[i] != null) {
		
					if (stuAndEmploy[i].getCodeObType()== 4) {
						countF ++;
						outFS.println("\t" + countF +". " + stuAndEmploy[i].getName());
						outFS.println("\t    ID: " + ((Employee)stuAndEmploy[i]).getId());
						outFS.println("\t    " + ((Faculty)(Employee)stuAndEmploy[i]).getRank() + ", " + ((Faculty)(Employee)stuAndEmploy[i]).getDepartment());
						outFS.println("\n");
						} 
					}//end if not null
			}//end for
					
			outFS.println("   Staff members");
			outFS.println("   -------------------");
			  for (int i=0; i<stuAndEmploy.length; i++) {
				if ( stuAndEmploy[i] != null) {
		
					if (stuAndEmploy[i].getCodeObType()== 5) {
						countStaff ++; 
						outFS.println("\t" + countStaff +". " + stuAndEmploy[i].getName());
						outFS.println("\t    ID: " + ((Employee)stuAndEmploy[i]).getId());
						outFS.println("\t    " + ((Staff)(Employee)stuAndEmploy[i]).getDepartment() + ", " + ((Staff)(Employee)stuAndEmploy[i]).getStatus());
						outFS.println("\n");
					}
					
					}//end if not null
				}//end for	
					
			 outFS.println("   Students");
			 outFS.println("   -------------");
			   for (int i=0; i<stuAndEmploy.length; i++) {
				 if ( stuAndEmploy[i] != null) {
						
					 if (stuAndEmploy[i].getCodeObType()== 2) {
						countStu++;
						outFS.println("\t" + countStu +". " + stuAndEmploy[i].getName());
						outFS.println("\t    ID: " + ((Student)stuAndEmploy[i]).getId());
						gpa = ((Student)stuAndEmploy[i]).getGpa();
						gpa = Math.round(gpa * 100.0)/100.0; //rounds to 2 decimal places
						creditHr = ((Student)stuAndEmploy[i]).getCreditHr();
						creditHr = Math.round(gpa * 10.0)/10.0; //rounds to one decimal place, some credit hours are .5 hour
						outFS.println("\t    Gpa: " + gpa); 
						outFS.println("\t    Credit hours: " + creditHr);
						outFS.println("\n");
					 }	 
					 
					}//end if not null
				}//end for     			  
		  
		  System.out.println("Report created!");

		  outFS.close();
	
	}//end method writeReport
	
	//gets the current date only and returns it as a string, removes java timestamp
	 public static String getDate() {
		  String sysDate;
		  SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
		  java.util.Date date = new java.util.Date();
		  sysDate =  dateFormat.format(date);
		  return sysDate;
	  }	//end method getDate
	 

}//end class Report

	