package club.pinea.school.config;

public enum PunishmentTypeEnum {

	WARING("警告"),
	
	SERIOUS_WARNING("严重警告"),
	
	REMEMBER_FAULT("记过处分")
	;
	
	private String name;
	
	PunishmentTypeEnum(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
