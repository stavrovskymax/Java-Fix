package ru.ivmiit.servlets;

public class CostCalculator {
	private double kmCost;
	private double kgCost;

	public CostCalculator(final double kmCost, final double kgCost) {
		this.kmCost = kmCost;             
		this.kgCost = kgCost;
	}

	
	public double calcCost(final double kg, final int km) {
		return kg * kgCost + km * kmCost;
	}
}