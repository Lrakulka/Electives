package ua.epam.electives.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.epam.electives.dao.ContractDao;
import ua.epam.electives.dao.CourseDao;
import ua.epam.electives.dao.DaoFactory;
import ua.epam.electives.dao.LecturerDao;
import ua.epam.electives.dao.StudentDao;
import ua.epam.electives.dao.jdbc.DaoJdbcConnection;
import ua.epam.electives.entities.Contract;
import ua.epam.electives.entities.Course;
import ua.epam.electives.entities.Lecturer;
import ua.epam.electives.entities.Student;

/**
 * JUnit test for DAO.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
@SuppressWarnings("serial")
public class Tests {
    private static ContractDao contractDao = DaoFactory.getDaoFactory()
	    .getContractDao();
    private static CourseDao courseDao = DaoFactory.getDaoFactory()
	    .getCourseDao();
    private static LecturerDao lecturerDao = DaoFactory.getDaoFactory()
	    .getLecturerDao();
    private static StudentDao studentDao = DaoFactory.getDaoFactory()
	    .getStudentDao();
    private static List<Lecturer> lecturers;
    private static List<Student> students;
    private static List<Course> courses;
    private static List<Contract> contracts;
    private static String[] lecturersPwd = { "1234", "234", "34" };
    private static String[] studentsPwd = { "4321", "321", "21", "1" };
    private static Lecturer lecturer;
    private static Student student;
    private static Course course;
    private static Contract contract;

    static {
	lecturer = new Lecturer(0, "Ptest", "qwerty");
	student = new Student(0, "Test", "ytrewq");
	course = new Course(0, "Course", 1);
	contract = new Contract(0, "comment", 12, 1, 1, (short) 12);
	lecturers = new ArrayList<Lecturer>() {
	    {
		add(new Lecturer(1, "Prepod1", lecturersPwd[0]));
		add(new Lecturer(2, "Prepod2", lecturersPwd[1]));
		add(new Lecturer(3, "Препод3", lecturersPwd[2]));
	    }
	};
	students = new ArrayList<Student>() {
	    {
		add(new Student(1, "Student1", studentsPwd[0]));
		add(new Student(2, "Student2", studentsPwd[1]));
		add(new Student(3, "Student3", studentsPwd[2]));
		add(new Student(4, "Student4", studentsPwd[3]));
	    }

	};
	courses = new ArrayList<Course>() {
	    {
		add(new Course(1, "Course1", 1));
		add(new Course(2, "Course2", 2));
		add(new Course(3, "Course3", 1));
		add(new Course(4, "Course4", 3));
	    }
	};
	contracts = new ArrayList<Contract>() {
	    {
		add(new Contract(1, null, null, 1, 1, (short) 30));
		add(new Contract(2, null, null, 2, 1, (short) 53));
		add(new Contract(3, "The most worst work ever", 0, 3, 2,
			(short) 100));
		add(new Contract(4, "Very bad", 4, 1, 3, (short) 100));
		add(new Contract(5, null, null, 2, 3, (short) 30));
		add(new Contract(6, null, null, 4, 4, (short) 23));
	    }
	};
    }

    @BeforeClass
    public static void insertDataToTables() {
	DaoJdbcConnection.startTestJdbcDao();
	String tablesName[] = { "lecturer", "student", "course", "contract" };
	for (int i = 0; i < tablesName.length; ++i) {
	    DaoJdbcConnection.cleanTable(tablesName[i]);
	}
	lecturerDao.insertAll(lecturers);
	studentDao.insertAll(students);
	courseDao.insertAll(courses);
	contractDao.insertAll(contracts);
    }

    @Test
    public void getAllLecturerData() {
	assertTrue(lecturerDao.getAll().containsAll(lecturers));
    }

    @Test
    public void getAllStudentData() {
	assertTrue(studentDao.getAll().containsAll(students));
    }

    @Test
    public void getAllContractData() {
	assertTrue(contractDao.getAll().containsAll(contracts));
    }

    @Test
    public void getAllCourseData() {
	assertTrue(courseDao.getAll().containsAll(courses));
    }

    @Test
    public void getFirstLecturer() {
	assertTrue(lecturerDao.getById(lecturers.get(0).getId()).equals(
		lecturers.get(0)));
    }

    @Test
    public void getFirstStudent() {
	assertTrue(studentDao.getById(students.get(0).getId()).equals(
		students.get(0)));
    }

    @Test
    public void getFirstContract() {
	assertTrue(contractDao.getById(contracts.get(0).getId()).equals(
		contracts.get(0)));
    }

    @Test
    public void getFirstCourse() {
	assertTrue(courseDao.getById(courses.get(0).getId()).equals(
		courses.get(0)));
    }

    @Test
    public void deleteLecturer() {
	lecturerDao.insert(lecturer);
	if (!lecturerDao.getAll().contains(lecturer)) {
	    fail();
	}
	lecturerDao.remove(lecturer.getId());
	assertFalse(lecturerDao.getAll().contains(lecturer));
    }

    @Test
    public void deleteStudent() {
	studentDao.insert(student);
	if (!studentDao.getAll().contains(student)) {
	    fail();
	}
	studentDao.remove(student.getId());
	assertFalse(studentDao.getAll().contains(student));
    }

    @Test
    public void deleteCourse() {
	courseDao.insert(course);
	if (!courseDao.getAll().contains(course)) {
	    fail();
	}
	courseDao.remove(course.getId());
	assertFalse(courseDao.getAll().contains(course));
    }

    @Test
    public void deleteContract() {
	contractDao.insert(contract);
	if (!contractDao.getAll().contains(contract)) {
	    fail();
	}
	contractDao.remove(contract.getId());
	assertFalse(contractDao.getAll().contains(contract));
    }

    @Test
    public void deleteContracts() {
	List<Contract> contractsList = new ArrayList<Contract>() {
	    {
		add(new Contract(contracts.get(0)));
		add(new Contract(contracts.get(1)));
	    }
	};
	contractDao.insertAll(contractsList);
	if (!contractDao.getAll().containsAll(contractsList)) {
	    fail();
	}
	contractDao.removeAll(contractsList);
	assertFalse(contractDao.getAll().contains(contractsList));
    }

    @Test
    public void deleteCourses() {
	List<Course> coursesList = new ArrayList<Course>() {
	    {
		add(new Course(courses.get(0)));
		add(new Course(courses.get(1)));
	    }
	};
	courseDao.insertAll(coursesList);
	if (!courseDao.getAll().containsAll(coursesList)) {
	    fail();
	}
	courseDao.removeAll(coursesList);
	assertFalse(courseDao.getAll().contains(coursesList));
    }

    @Test
    public void deleteLecturers() {
	List<Lecturer> lecturersList = new ArrayList<Lecturer>() {
	    {
		add(new Lecturer(lecturers.get(0)));
		add(new Lecturer(lecturers.get(1)));
	    }
	};
	lecturerDao.insertAll(lecturersList);
	if (!lecturerDao.getAll().containsAll(lecturersList)) {
	    fail();
	}
	lecturerDao.removeAll(lecturersList);
	assertFalse(lecturerDao.getAll().contains(lecturersList));
    }

    @Test
    public void deleteStudents() {
	List<Student> studentsList = new ArrayList<Student>() {
	    {
		add(new Student(students.get(0)));
		add(new Student(students.get(1)));
	    }
	};
	studentDao.insertAll(studentsList);
	if (!studentDao.getAll().containsAll(studentsList)) {
	    fail();
	}
	studentDao.removeAll(studentsList);
	assertFalse(studentDao.getAll().contains(studentsList));
    }

    @Test
    public void updateContract() {
	contracts.get(0).setFinishedPercent((short) 45);
	assertTrue(contractDao.update(contracts.get(0)));
    }

    @Test
    public void updateCourse() {
	courses.get(0).setName(courses.get(0).getName() + "p");
	assertTrue(courseDao.update(courses.get(0)));
    }

    @Test
    public void updateLecturer() {
	lecturers.get(0).setFullName(lecturers.get(0).getFullName() + "p");
	assertTrue(lecturerDao.update(lecturers.get(0)));
    }

    @Test
    public void updateStudent() {
	students.get(0).setFullName(students.get(0).getFullName() + "p");
	assertTrue(studentDao.update(students.get(0)));
    }

    @Test
    public void updateContracts() {
	contracts.get(0).setFinishedPercent((short) 100);
	contracts.get(1).setFinishedPercent((short) 100);
	assertEquals(contractDao.updateAll(contracts), contracts.size());
    }

    @Test
    public void updateCourses() {
	courses.get(0).setName(courses.get(0).getName() + "p");
	courses.get(1).setName(courses.get(1).getName() + "p");
	assertEquals(courseDao.updateAll(courses), courses.size());
    }

    @Test
    public void updateLecturers() {
	lecturers.get(0).setFullName(lecturers.get(0).getFullName() + "p");
	lecturers.get(1).setFullName(lecturers.get(1).getFullName() + "p");
	assertEquals(lecturerDao.updateAll(lecturers), lecturers.size());
    }

    @Test
    public void updateStudents() {
	students.get(0).setFullName(students.get(0).getFullName() + "p");
	students.get(1).setFullName(students.get(1).getFullName() + "p");
	assertEquals(studentDao.updateAll(students), students.size());
    }

    @Test
    public void getCourseContracts() {
	assertTrue(contracts.containsAll(contractDao.getCourseContracts(1)));
    }

    @Test
    public void getStudentContracts() {
	assertTrue(contracts.containsAll(contractDao.getStudentContracts(1)));
    }

    @Test
    public void getLecturerCourses() {
	assertTrue(courses.containsAll(courseDao.getLecturerCourses(1)));
    }

    @Test
    public void authorizeFailed() {
	assertTrue(DaoFactory.getDaoFactory().getAuthorizeUser()
		.authorize("sfd", "-1-21") == null);
    }

    @Test
    public void authorizeLecturer() {
	assertTrue(DaoFactory.getDaoFactory().getAuthorizeUser()
		.authorize(lecturers.get(1).getFullName(), lecturersPwd[1])
		.equals(lecturers.get(1)));
    }

    @Test
    public void authorizeStudent() {
	assertTrue(DaoFactory.getDaoFactory().getAuthorizeUser()
		.authorize(students.get(1).getFullName(), studentsPwd[1])
		.equals(students.get(1)));
    }

    @Test
    public void getAllStudent() {
	assertTrue(DaoFactory
		.getDaoFactory()
		.getStudentDao()
		.getAllStudent(contracts)
		.containsAll(
			DaoFactory.getDaoFactory().getStudentDao().getAll()));
    }

    @AfterClass
    public static void cleanDataFromTables() {
	lecturerDao.removeAll(lecturers);
	studentDao.removeAll(students);
	courseDao.removeAll(courses);
	contractDao.removeAll(contracts);
	DaoJdbcConnection.stopTestJdbcDao();
    }
}
