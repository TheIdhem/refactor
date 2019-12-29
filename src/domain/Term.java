package domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Term {
	private String name;
	private Date startDate;
	private List<Offering> offerings;

	public Term(String name) {
		this.name = name;
		this.startDate = null;
		offerings = new ArrayList<Offering>();
	}

	public Term(String name, Date startDate) {
		this.name = name;
		this.startDate = startDate;
		offerings = null;
	}
	
	public void addOffering(Offering c) {
		getOfferings().add(c);
	}

	public void addAllOfferings(Collection<Offering> c) {
		getOfferings().addAll(c);
	}

	public List<Offering> getOfferings() {
		return offerings;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	
}
