import java.util.ArrayList;

public class Layer 
{
	private ArrayList<Node> layer = new ArrayList<Node>();
	private double[][] weights;
	private int numberOfNodesInPrevLayer;
	private double[] biases;
	private int whichLayerIsThis;
	
	public Layer(int whichLayerIsThis, int numberOfNodesInThisLayer, int numberOfNodesInPrevLayer)
	{
		this.whichLayerIsThis = whichLayerIsThis;
		for(int i = 0; i < numberOfNodesInThisLayer; i++)
		{
			layer.add(new Node());
		}	
		
		this.numberOfNodesInPrevLayer = numberOfNodesInPrevLayer;
		weights = new double[numberOfNodesInThisLayer][numberOfNodesInPrevLayer];
		for (int i = 0; i < weights[0].length; i++)
		{
			for (int j = 0; j < weights.length; j++)
			{
				 weights[i][j] = (Math.random()*2)-1;
			}
		}
		
		biases = new double[numberOfNodesInThisLayer];
		for (int i = 0; i < numberOfNodesInThisLayer; i++) 
		{
			biases[i] = (Math.random()*2)-1;
		}
		
	}
	
	public void runNetwork()
	{
		//Multiply weights matrix by activation values of nodes in layer, 
		double weightedSum;
		for (int i = 0; i < weights.length; i++)
		{
			weightedSum = 0;
			for (int j = 0; i < weights[0].length; j++)
			{
				weightedSum = weightedSum + ( weights[i][j] * (layer.get(j)).getActivationValue() );
			}
			(layer.get(i)).setWeightedSum(weightedSum);
			// add bias and squishify
			weightedSum = squishify(weightedSum + biases[i]);
			//set activation value of node in this layer to weightedSum
			(layer.get(i)).setActivationValue(weightedSum);
		}
	}
		
	public double squishify(double x)
	{
		//sigmoid function to compress values between 0 and 1
		double y;
		if( x < -10 )
		    y = 0;
		else if( x > 10 )
		    y = 1;
		else
		    y = 1 / (1 + Math.exp(-x));
		return y;		 
	}
	
	public int setWeights(int index, ArrayList<Double> negativeGradient)
	{
		for (int i = 0; i < weights.length; i++)
		{
			
			for (int j = 0; i < weights[0].length; j++)
			{
				weights[i][j] = weights[i][j] + (0.1 * negativeGradient.get(index));
				index++;
			}
			biases[i] = biases[i] + negativeGradient.get(index);
			index++;
		}
		return index;
	}
	
	public Node getNode( int index )
	{
		
		return layer.get(index);
	}
	
	public int getNumberOfNodesInPrevLayer()
	{
		return numberOfNodesInPrevLayer;
	}
	
	public int getnumberOfNodesInThisLayer()
	{
		return layer.size();
	}
	
	public int getWhichLayerIsThis()
	{
		return whichLayerIsThis;
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
