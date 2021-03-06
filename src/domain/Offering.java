package domain;
import java.util.Date;

public class Offering {
	private String id;
	private Course course;
	private int section;
	private Date examDate;
	private Term term;
	
	public Offering(Course course, Term term) {
		this.course = course;
		this.section = 1;
		this.examDate = null;
		this.term = term;
	}

	public Offering(Course course, Term term, Date examDate) {
		this.course = course;
		this.section = 1;
		this.examDate = examDate;
		this.term = term;
	}

	public Offering(String id, Course course, Term term, Date examDate, int section) {
		this.id = id;
		this.course = course;
		this.section = section;
		this.examDate = examDate;
		this.term = term;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public String toString() {
		return id + ":\t" + course.getName() + " - " + section + " [" + term + "]";
	}
	
	public Date getExamTime() {
		return examDate;
	}

	public int getUnits() {
		return course.getUnits();
	}
	
	public String getId() {
		return id;
	}
	
	public Term getTerm() {
		return term;
	}
}
