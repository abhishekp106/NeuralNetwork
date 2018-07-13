import java.util.ArrayList;


public class Node {
	private double[] inputs;
	private double activationValue;
	private double weightedSum;
	
	public Node() 
	{
		
	}
	
	//Getters
	public double getActivationValue()
	{
		return activationValue;
	}
	
	public double getWeightedSum()
	{
		return weightedSum;
	}
	
	//Setters
	
	public void setActivationValue(double newValue)
	{
		activationValue = newValue;
	}
	
	public void setWeightedSum(double weightedSum)
	{
		this.weightedSum = weightedSum;
	}
	
	

}
