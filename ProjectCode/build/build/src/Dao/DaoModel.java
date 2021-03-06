package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.*;
import models.AcademicMember;
import models.DataModel;

/**
 * @author Pablo Angel Alvarez Fernandez
 * @author Pablo Fernandez Diaz
 * DaoModel class
 * Dao stands for Data Access Object, defines 
 * CRUD (Create Read Update Delete) like operations
 * 
 * Methods name auto-explicatives
 */
public class DaoModel {
	//static Connection con = null;
	//static DaoModel DB = null;
	//private boolean created = false;

	//public DaoModel() { // create db object instance
		//if(!created) new DBCreate(); created = true;
	//}

	public static void createTables(){

		//QueryUpd("ALTER TABLE papf_courseStudents ADD eGrade numeric(2,2);");
		//QueryUpd("ALTER TABLE papf_courseStudents MODIFY eGrade numeric(4,2);");
		//QueryUpd("ALTER TABLE papf_students MODIFY eGPA numeric(4,2);");
		//QueryUpd("ALTER TABLE papf_students MODIFY ePassword VARCHAR(64);"); //lenght sha-256 in char equals to 64 always
		//QueryUpd("ALTER TABLE papf_professors MODIFY pPassword VARCHAR(64);"); //lenght sha-256 in char equals to 64 always
		//String hashPass = models.HashingModel.hash("pass");
		/*String sql = "Update papf_students SET ePassword = '"+hashPass+"'  WHERE eEMail = 'pfernandezdiaz@hawk.iit.edu';";
		String sql2 = "Update papf_students SET ePassword = '"+hashPass+"'  WHERE eEMail = 'palvarezfernandez@hawk.iit.edu';";
		String sql3 = "Update papf_students SET ePassword = '"+hashPass+"'  WHERE eEMail = 'jjonnes20@hawk.iit.edu';";
		String sql4 = "Update papf_students SET ePassword = '"+hashPass+"'  WHERE eEMail = 'shernandez8@hawk.iit.edu';";
		String sql5 = "Update papf_students SET ePassword = '"+hashPass+"'  WHERE eEMail = 'lrajoy@hawk.iit.edu';";
		DaoModel.QueryUpd(sql);
		DaoModel.QueryUpd(sql2);
		DaoModel.QueryUpd(sql3);
		DaoModel.QueryUpd(sql4);
		DaoModel.QueryUpd(sql5);*/
		/*String sql1 = "Update papf_professors SET pPassword = '"+hashPass+"'  WHERE pEMail = 'lpapademas@iit.edu';";
		String sql12 = "Update papf_professors SET pPassword = '"+hashPass+"'  WHERE pEMail = 'jpapademas@iit.edu';";
		String sql13 = "Update papf_professors SET pPassword = '"+hashPass+"'  WHERE pEMail = 'dmo@iit.edu';";
		String sql14 = "Update papf_professors SET pPassword = '"+hashPass+"'  WHERE pEMail = 'rrao@iit.edu';";
		String sql15 = "Update papf_professors SET pPassword = '"+hashPass+"'  WHERE pEMail = 'jhajek@iit.edu';";
		DaoModel.QueryUpd(sql1);
		DaoModel.QueryUpd(sql12);
		DaoModel.QueryUpd(sql13);
		DaoModel.QueryUpd(sql14);
		DaoModel.QueryUpd(sql15);*/
		
		// SELECT to check the content of the table
		/*ResultSet rs = null;
		rs = QueryResu("SELECT * FROM papf_professors;");
		try {
			while(rs.next()){
				System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(6)+"   "+rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		new DBCreate();
	}

	/**
	 * @param robjs (list of records)
	 * INSERT INTO METHOD
	 */
	public static void insertStudent(Student objs) {
		System.out.println("Inserting records into the table...");
		String sql = "INSERT INTO papf_students(eFName, eLName, eEMail, ePassword, eMaj, eGPA) " + 
				"VALUES ('"+objs.getFirstName()+"', '"+objs.getLastName()+"', '"+objs.getEmail()+"','', '"+objs.getMajor()+"', "+objs.getGpa()+")";
		if(QueryUpd(sql)){
			System.out.println("Element inserted.");
		} else {
			DataModel.sendAlert("Insert error", "Not inserted");
		}
	}

	/**
	 * @param robjs (list of records)
	 * INSERT INTO METHOD
	 */
	public static void insertProfessor(Professor objs) {
		System.out.println("Inserting records into the table...");
		String sql = "INSERT INTO papf_professors(pFName, pLName, pEMail,pPassword, pDept, pOffi) " + 
				"VALUES ('"+objs.getFirstName()+"', '"+objs.getLastName()+"', '"+objs.getEmail()+"','', '"+objs.getDept()+"', "+objs.getOfficeNo()+")";
		if(QueryUpd(sql)){
			System.out.println("Element inserted.");
		} else {
			DataModel.sendAlert("Insert error", "Not inserted");
		}
	}

	/**
	 * @param robjs (list of records)
	 * INSERT INTO METHOD
	 */
	public static void insertUniversity(University objs) {
		System.out.println("Inserting records into the table...");
		String sql = "INSERT INTO papf_universities( uAcronym, uName, uCity, uZipCode) " + 
				"VALUES ('"+objs.getUniAcronym()+"','"+objs.getUniName()+"','"+objs.getUniCity()+"',"+objs.getUniZipCode()+");";
		if(QueryUpd(sql)){
			System.out.println("Element inserted.");
		} else {
			DataModel.sendAlert("Insert error", "Not inserted");
		}
	}

	/**
	 * @param robjs (list of records)
	 * INSERT INTO METHOD
	 */
	public static void insertCourse(Course objs) {
		System.out.println("Inserting records into the table...");
		String sql = "INSERT INTO papf_courses(cName, cCredits, cProf, cUni)"+ 
				"VALUES ('"+objs.getcName()+"', "+objs.getnCredits()+", "+objs.getProf().getId()+", '"+objs.getUni().getUniAcronym()+"')";
		if(QueryUpd(sql)){
			System.out.println("Element inserted.");
		} else {
			DataModel.sendAlert("Insert error", "Not inserted");
		}
	}	

	/**
	 * @param eGrade 
	 * @param robjs (list of records)
	 * INSERT INTO METHOD
	 */
	public static void insertCourseStudents(int cID, int eID, double eGrade) {
		System.out.println("Inserting records into the table...");
		String sql = "INSERT INTO papf_courseStudents(cID, eID, eGrade) VALUES ("+cID+", "+eID+","+eGrade+")";
		if(QueryUpd(sql)){
			System.out.println("Element inserted.");
		} else {
			DataModel.sendAlert("Insert error", "Not inserted");
		}
	}	


	public static University selectUniversity(String nombreUni) {
		ResultSet rs = null;
		String sql = "SELECT * FROM papf_universities WHERE uAcronym = '"+nombreUni+"';";
		rs = QueryResu(sql);
		try {
			if(rs.next()){
				return new University(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));				
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return null; 
	}

	public static Professor selectProfessor(int offN) {
		ResultSet rs = null;
		String sql = "SELECT * FROM papf_professors WHERE pOffi = "+offN+";";
		rs = QueryResu(sql);		
		try {
			if(rs.next()){
				return new Professor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getInt(7));
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return null; 
	}

	public static Professor selectProfessor2(int pID) {
		ResultSet rs = null;
		String sql = "SELECT * FROM papf_professors WHERE pID = "+pID+";";
		rs = QueryResu(sql);
		try {
			if(rs.next()){
				return new Professor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getInt(7));				
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return null; 
	}

	public static Student selectStudent(String lName) {
		ResultSet rs = null;
		String sql = "SELECT * FROM papf_students WHERE eLName = '"+lName+"';";
		rs = QueryResu(sql);
		try {
			if(rs.next()){
				return new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6));				
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return null; 
	}

	public static Course selectCourse(String cName) {
		ResultSet rs = null;
		String sql = "SELECT * FROM papf_courses WHERE cName = '"+cName+"';";
		rs = QueryResu(sql);
		try {
			if(rs.next()){
				return new Course(rs.getInt(1),rs.getString(2),rs.getInt(3),selectProfessor2(rs.getInt(4)),selectUniversity(rs.getString(5))); // Sacar los estudiantes del curso				
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return null; 
	}

	public static String selectCourseName(int id) {
		ResultSet rs = null;
		String sql = "SELECT cName FROM papf_courses WHERE cID = '"+id+"';";
		rs = QueryResu(sql);
		try {
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return null;
	}

	// Seleccionamos los Profesores que imparten un curso determinado
	public static String[][] selectCoursesTeachProfessor(int cProf) {	
		ResultSet rs = null;
		String sql = "SELECT cID, cName FROM papf_courses WHERE cProf = "+cProf+";";
		rs = QueryResu(sql);
		String[][] cursos = new String[100][2];
		int aux = 0;
		try {
			while(rs.next()){

				cursos[aux][0] = Integer.toString(rs.getInt(1));
				cursos[aux][1] = rs.getString(2);

				aux++;
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return cursos;				
	}
	
	public static String selectStudentsNameOfCourse(int eID) {	
		ResultSet rs = null;
		String sql = "SELECT eFName, eLName FROM papf_students WHERE eID = "+eID+";";
		rs = QueryResu(sql);
		
		//String[] studentsName = new String[100];
		String studentsName="";
		
		//int aux = 0;
		try {

			while(rs.next()){
				//studentsName[aux] = rs.getString(1) +" "+ rs.getString(2);
				studentsName = rs.getString(1) +" "+ rs.getString(2);
			}
				
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return studentsName;	
	}

	public static String[] selectStudentsOfCourse(int cID) {	
		ResultSet rs = null;
		String sql = "SELECT eID FROM papf_courseStudents WHERE cID = "+cID+";";
		rs = QueryResu(sql);
		
		String[] students = new String[100];
		
		int aux = 0;
		try {
			while(rs.next()){
				students[aux] = Integer.toString(rs.getInt(1));
				aux++;
			}				
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return students;				
	}
	
	/**
	 * @return record retrieved
	 * retrieveRecords
	 */
	public ResultSet retrieveStudentInfo() {//always close out of your connections 
		System.out.println("Retrieving Records...");
		String sql = "SELECT * FROM papf_students";
		System.out.println("Records are now retrieved");

		return QueryResu(sql);
	}

	/**
	 * @return record retrieved
	 * retrieveRecords
	 */
	public ResultSet retrieveProfessorInfo() {//always close out of your connections 
		System.out.println("Retrieving Records...");
		String sql = "SELECT * FROM papf_professors";
		System.out.println("Records are now retrieved");

		return QueryResu(sql);
	}

	/**
	 * @return record retrieved
	 * retrieveRecords
	 */
	public ResultSet retrieveUniversityInfo() {//always close out of your connections 
		System.out.println("Retrieving Records...");
		String sql = "SELECT * FROM papf_universities";
		System.out.println("Records are now retrieved");

		return QueryResu(sql);
	}

	/**
	 * @return record retrieved
	 * retrieveRecords
	 */
	public ResultSet retrieveCourseInfo() {//always close out of your connections 
		System.out.println("Retrieving Records...");
		String sql = "SELECT * FROM papf_courses";
		System.out.println("Records are now retrieved");

		return QueryResu(sql);
	}

	/**
	 * @return record retrieved
	 * retrieveRecords
	 */
	public ResultSet retrieveCourseStudentInfo() {//always close out of your connections 
		System.out.println("Retrieving Records...");
		String sql = "SELECT * FROM papf_courseStudents";
		System.out.println("Records are now retrieved");

		return QueryResu(sql);
	}

	public static void updateStudent(int id, String fName, String lName, String major) {
		String sql = "UPDATE papf_students SET eFName='"+fName+"', eLName='"+lName+"', eMaj='"+major+"' WHERE eID="+id+";";
		if(QueryUpd(sql)){
			System.out.println("Element updated.");
		} else {
			DataModel.sendAlert("Update error", "Not inserted");
		}
	}

	public static void updateProfessor(int id, String fName, String lName, String department, int office) {
		String sql = "UPDATE papf_professors SET pFName='"+fName+"', pLName='"+lName+"', pDept='"+department+"', pOffi="+office+" WHERE pID="+id+";";
		if(QueryUpd(sql)){
			System.out.println("Element updated.");
		} else {
			DataModel.sendAlert("Update error", "Not inserted");
		}	}

	public static void updateCourseStudentGrade(int cID, int eID, double eGrade) {
		String sql = "UPDATE papf_courseStudents SET eGrade="+Double.toString(eGrade)+" WHERE eID="+eID+" AND cID="+cID+";";
		if(QueryUpd(sql)){
			System.out.println("Element updated.");
		} else {
			DataModel.sendAlert("Update error", "Not inserted");
		}	}
	
	public static void updateStudentGPA(int eID) {
		Grade[] grad = getStdCoursesGPA(eID);
		double grades_sum = 0.0;
		int count_courses = 0;
		int i=0;
		// pass decimal note to GPA
		while(grad[i] != null) {
			if(grad[i].getGrade() != 0.0) {
				if(grad[i].getGrade() >= 9) {
					grades_sum += 4.0;
				} else if(grad[i].getGrade() >= 7.5) {
					grades_sum += 3.0;
				} else if(grad[i].getGrade() >= 6) {
					grades_sum += 2.0;
				} else if(grad[i].getGrade() >= 5) {
					grades_sum += 1.0;
				} else if(grad[i].getGrade() >= 0) {
					grades_sum += 0.0;
				} 
				count_courses+=3; // always consider 3 credit per course
			}
			i++;
		}
		double gpa = grades_sum*count_courses/count_courses;
		System.out.println(Double.toString(gpa));
		String sql = "UPDATE papf_students SET eGPA="+Double.toString(gpa)+" WHERE eID="+eID+";";
		if(QueryUpd(sql)){
			System.out.println("Element updated.");
		} else {
			DataModel.sendAlert("Update error", "Not inserted");
		}
		//Student std = selectStudent("Alvarez");
		//System.out.println(std.getGpa());
	}

	private static Grade[] getStdCoursesGPA(int eID) {
		ResultSet rs = null;
		String sql = "SELECT cID, eGrade FROM papf_courseStudents WHERE eID ="+eID+";";
		rs = QueryResu(sql);
		
		Grade[] grad = new Grade[100];
		
		int aux = 0;
		Student std = new Student(eID,null,null,null,null);
		Course c = null;
		try {
			while(rs.next()){
				c = new Course(rs.getInt(1),null,0,(Professor)null,(University)null);
				grad[aux] = new Grade(std, c, rs.getDouble(2));
				aux++;
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}
		return grad;
	}

	public static ResultSet getStudentGrades(int id){
		String sql = "SELECT cID, eGrade FROM papf_courseStudents WHERE eID = "+id+";";
		return QueryResu(sql);
	}

	public static ResultSet QueryResu(String sentencia) {
		// querys que devuelven resultados
		Connection con = DBConnect.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sentencia);
			// con.close();
			return result;
		} catch (Exception e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
			return null;
		}
	}

	public static boolean QueryUpd(String sentencia) {
		// querys que no devuelven resultados
		Connection con = DBConnect.getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sentencia);
			stmt.close();
			con.close();
			return true;
		} catch (Exception e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
			return false;
		}
	}

	public AcademicMember login(String user, String pass) {
		ResultSet rs = null;
		AcademicMember AMaux = null;

		rs = QueryResu("SELECT * FROM papf_students WHERE eEMail = '"+user+"' AND ePassword ='"+ pass +"';");
		try {
			if(rs.next()){
				AMaux = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getDouble(7));	
				AMaux.setEmail(AMaux.getEmail().substring(0,AMaux.getEmail().length()-13));
			}
		} catch (SQLException e) {
			DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
			e.printStackTrace();
		}

		if(AMaux == null){
			rs = QueryResu("SELECT * FROM papf_professors WHERE pEMail = '"+user+"' AND pPassword ='"+ pass +"';");
			try {
				if(rs.next()){
					AMaux = new Professor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getInt(7));
					AMaux.setEmail(AMaux.getEmail().substring(0,AMaux.getEmail().length()-8));
				}
			} catch (SQLException e) {
				DataModel.sendAlert("Show error", "Please talk to admin: "+e.toString().substring(0, 40));
				e.printStackTrace();
			}
		}
		if(AMaux == null) {
			DataModel.sendAlert("User not found", "Please review the data");
		}
		return AMaux; 
	}
}
