package club.pinea.school.config;

public enum NationEnum {
	HAN(1, "汉族"), 
	ZHUANG(2, "壮族"), 
	MANCHU(3, "满族"), 
	HUI(4, "回族"), 
	MIAO(5, "苗族"), 
	UYGHUR(6, "维吾尔族"), 
	YI(7, "彝族"), 
	TUJIA(8, "土家族"), 
	MONGOL(9, "蒙古族"), 
	TIBETAN(10, "藏族"), 
	BUYEI(11, "布依族"), 
	DONG(12, "侗族"), 
	YAO(13, "瑶族"), 
	KOREAN(14, "朝鲜族"), 
	BAI(15, "白族"), 
	HANI(16, "哈尼族"), 
	LI(17, "黎族"), 
	KAZAK(18, "哈萨克族"), 
	DAI(19, "傣族"), 
	SHE(20, "畲族"), 
	LISU(21, "僳僳族"), 
	GELAO(22, "仡佬族"), 
	LAHU(23, "拉祜族"), 
	DONGXIANG(24, "东乡族"), 
	VA(25, "佤族"), 
	SUI(26, "水族"), 
	NAXI(27, "纳西族"), 
	QIANG(28, "羌族"), 
	TU(29, "土族"), 
	XIBE(30, "锡伯族"), 
	MULAO(31, "仫佬族"), 
	KIRGIZ(32, "柯尔克孜族"), 
	DAUR(33, "达斡尔族"), 
	JINGPO(34, "景颇族"), 
	SALAR(35, "撒拉族"), 
	BLANG(36, "布朗族"), 
	MAONAN(37, "毛南族"), 
	TAJIK(38, "塔吉克族"), 
	PUMI(39, "普米族"), 
	ACHANG(40, "阿昌族"), 
	NU(41, "怒族"), 
	EWENKI(42, "鄂温克族"), 
	GIN(43, "京族"), 
	JINO(44, "基诺族"), 
	DEANG(45, "德昂族"), 
	UZBEK(46, "乌孜别克族"), 
	RUSSIANS(47, "俄罗斯族"), 
	YUGUR(48, "裕固族"), 
	BONAN(49, "保安族"),
	MONBA(50, "门巴族"),
	OROQEN(51, "鄂伦春族"),
	DERUNG(52, "独龙族"), 
	TATAR(53, "塔塔尔族"), 
	HEZHEN(54, "赫哲族"), 
	LHOBA(55, "珞巴族"), 
	GAOSHAN(56, "高山族");

	// 枚举值
	private final int key;

	// 枚举描述
	private final String desc;

	private NationEnum(int key, String desc) {
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 获取民族的排序
	 * @return
	 */
	public int getKey() {
		return this.key;
	}
	
	/**
	 * 获取当前民族的中文名称
	 * @return
	 */
	public String getDesc() {
		return this.desc;
	}
}
