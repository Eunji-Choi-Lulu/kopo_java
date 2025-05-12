package midterm;

/*
 * 연, 월 배열 
 */
public class Year {
	
	int year;
	Month[] months = new Month[12];

	public Year(int year) {
		this.year = year;
	}

	public void setMonth(int index, Month month) {
		months[index] = month;
	}

	public void printYearSummary() {
		System.out.println("==== " + year + "년 요약 ====");
		for (int i = 0; i < 12; i++) {
			months[i].report();
		}
	}
}