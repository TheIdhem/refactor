package domain;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private String id;
	private String name;
	
	private List<StudyRecord> studyRecords;

	public Student(String id, String name) {
		this.id = id;
		this.name = name;
		this.studyRecords = new ArrayList<StudyRecord>();
	}
	
	public void takeOffering(Offering c) {
		getStudyRecords().add(new StudyRecord(c));
	}

	public void takeOffering(Offering c, double grade) {
		getStudyRecords().add(new StudyRecord(c, grade));
	}

	public List<StudyRecord> getStudyRecords() {
		return studyRecords;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
