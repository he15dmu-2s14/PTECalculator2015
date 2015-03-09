package domain;

public interface Kraft {

	/**
	 * Denne metode bruges til at saette vinklen. Dette goeres vha. et parameter fra ekspert-klassen Vinkel
	 * @param vinkel er vinklen angivet i grader
	 */
	public void setVinkel(Vinkel vinkel);

	/**
	 * Denne metode bruges til at saette belastningen. Dette goeres vha. et parameter fra ekspert-klassen Belastning
	 * @param belastning er belastningen angivet i N, kg el. ton
	 */
	public void setBelastning(Belastning belastning);
}
