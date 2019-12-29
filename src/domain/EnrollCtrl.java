package domain;

import java.util.List;
import domain.exceptions.EnrollmentRulesViolationException;

public class EnrollCtrl {
	public void enroll(Student s, List<Offering> ol) throws EnrollmentRulesViolationException {
		// check the student has passed all the pre-requisites
		for (Offering o : ol) {
			List<Course> prereqs = o.getCourse().getPrerequisites();
			nextPre:
			for (Course pre : prereqs) {
				List<StudyRecord> transcript = s.getStudyRecords();
				for (StudyRecord sr : transcript) {
					if (sr.getOffering().getCourse().equals(pre) && sr.getGrade() >= 10)
						continue nextPre;
				}
				throw new EnrollmentRulesViolationException(String.format("The student has not passed %s as a prerequisite of %s", pre.getName(), o.getCourse().getName()));
			}
		}
		// check the student has not already passed the courses
		for (Offering o : ol) {
			List<StudyRecord> transcript = s.getStudyRecords();
			for (StudyRecord sr : transcript) {
				if (sr.getOffering().getCourse().equals(o.getCourse()) && sr.getGrade() >= 10)
					throw new EnrollmentRulesViolationException(String.format("The student has already passed %s", o.getCourse().getName()));
			}
		}
		// check no two offerings have the same exam time
		for (Offering o1 : ol) {
			for (Offering o2 : ol) {
				if (o1 == o2)
					continue;
				if (o1.getExamTime().equals(o2.getExamTime()))
					throw new EnrollmentRulesViolationException(String.format("Two offerings %s and %s have the same exam time", o1, o2));
			}
		}
		// check no course has taken twice
		for (Offering o1 : ol) {
			for (Offering o2 : ol) {
				if (o1 == o2)
					continue;
				if (o1.getCourse().equals(o2.getCourse()))
					throw new EnrollmentRulesViolationException(String.format("%s is requested to be taken twice", o1.getCourse().getName()));
			}
		}
		// check the number of credit units matches GPA limit
		int unitsRequested = 0;
		for (Offering o : ol)
			unitsRequested += o.getUnits();
		double points = 0;
		int totalUnits = 0;
		for (StudyRecord sr : s.getStudyRecords()) {
			points += sr.getGrade() * sr.getUnits();
			totalUnits += sr.getUnits();
		}
		double gpa = points / totalUnits;
		if ((gpa < 12 && unitsRequested > 14) ||
				(gpa < 16 && unitsRequested > 16) ||
				(unitsRequested > 20))
			throw new EnrollmentRulesViolationException(String.format("Number of units (%d) requested does not match GPA of %f", unitsRequested, gpa));
		for (Offering o : ol)
			s.takeOffering(o);
	}
}
