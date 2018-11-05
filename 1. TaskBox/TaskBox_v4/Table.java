import ru.ivmiit.Box;

public class Table implements Box {
	private double weight;
	private int countOfLegs;

	public Table(final double weight, final int countOfLegs) {
		this.weight = weight;
		this.countOfLegs = countOfLegs;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}
}