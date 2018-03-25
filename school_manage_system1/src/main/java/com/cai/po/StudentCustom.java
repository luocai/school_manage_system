package com.cai.po;

import java.util.List;

public class StudentCustom extends Student {
	
	@Override
	public String toString() {
		return "StudentCustom [collegeName=" + collegeName + ", selectedCourseList=" + selectedCourseList + "]";
	}

	private String collegeName;
	
	private List<SelectedcourseCustom> selectedCourseList;

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public List<SelectedcourseCustom> getSelectedCourseList() {
		return selectedCourseList;
	}

	public void setSelectedCourseList(List<SelectedcourseCustom> selectedCourseList) {
		this.selectedCourseList = selectedCourseList;
	}
	
	
}
