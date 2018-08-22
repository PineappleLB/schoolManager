package club.pinea.school.config;

public enum CoursesEnum {
	
	MATH("数学"),
	ENGLISH("英语"),
	CHINESE("语文"),
	COMPUTRE("计算机"),
	PHYSICS("物理")
	;
	/**
	 * 课程中文名称
	 */
	private String coursesName;
	
	CoursesEnum(String name){
		this.coursesName = name;
	}
	
	/**
	 * 获取课程中文名称
	 * @return
	 */
	public String getName() {
		return this.coursesName;
	}
	
}
