import java.util.ArrayList;


public class learning 
{
	Layer lastLayer = new Layer(4, 10, 16);
	ArrayList<Double> negativeGradient = new ArrayList<Double>(14000);
	int trainingBatchSize = 50;
	int donteverdothisagain = 0;
	
	/*
	public double[] calculateCost(Layer lastLayer, double[] expectedValue)
	{
		this.lastLayer = lastLayer;
		double[] cost = new double[10];
		for(int i = 0; i < lastLayer.getnumberOfNodesInThisLayer(); i++)
		{
			double calculatedValue = (lastLayer.get(i)).getActivationValue();
			cost[i] = Math.pow((calculatedValue - expectedValue[i]),2);
			//negativeGradient.add( 2 * (calculatedValue - expectedValue[i]) );
		}
		
		return cost;
	} 
	*/
	
	public double sigmoid_prime(double input)
	{
		 double result = squishify(input) * (1-squishify(input));
		 return result;
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
	
	public void trainInBatches(Layer lastLayers, double[] expectedValue, ArrayList<Double> tempNegativeGradient)
	{
		for (int i = 0; i < trainingBatchSize; i++)
		{
			backpropagation(lastLayer, expectedValue);
			
		}
		
		for (int i= 0; i < negativeGradient.size(); i++)
		{
			negativeGradient.set(i, tempNegativeGradient);
		}
	}
	
	public double accuracy()
	{
		//this method should return the accuracy of the network after each training batch
		//this is designed to measure if it is getting better
		return 0.0;
	}
	
	public ArrayList<Double> backpropagation(Layer lastLayer, double[] expectedValue)
	{
		Network network = new Network();
		// start at the first neuron in the last layer, and calculate the cost
		ArrayList<Double> tempNegativeGradient = new ArrayList<Double>(14000);
		
		if (donteverdothisagain == 0) 
		{
			for(int i = 0; i < tempNegativeGradient.size(); i++)
			{
				tempNegativeGradient.set(i, 0.0);
			}
			donteverdothisagain++;
		}
		
		//repeat this backpropagation process for the number of images in the training batches, and then update
		double costDerivative; //dC/da = 2(a^L - y)
		double activationDerivative; // da/dz = sigmoid'
		double weightedSumDerivative; // dz/dw = activation value
		double partialProduct;
		double finalDerivative;
		double accumulateddeltas;
		
		int index = 0;
		
			for (int i = 0; i < lastLayer.getnumberOfNodesInThisLayer(); i++)
			{
				costDerivative = 2 * ((lastLayer.getNode(i)).getActivationValue() - expectedValue[i]);
				activationDerivative = sigmoid_prime( (lastLayer.getNode(i)).getWeightedSum() );
				partialProduct = costDerivative * activationDerivative;
			
				for (int j = 0; j < lastLayer.getNumberOfNodesInPrevLayer(); j++)
				{
					//loops through all nodes of previous layer
					weightedSumDerivative = ((network.accessNetworkLayer(lastLayer.getWhichLayerIsThis() -1)).getNode(j)).getActivationValue();
					finalDerivative = partialProduct * weightedSumDerivative;
					//what value for the index?
					tempNegativeGradient.add(-1 * finalDerivative);
					index++;
				}
				tempNegativeGradient.add(-1 * partialProduct);
				index++;
			}
		
		//fix recursion its gonna return multiple array lists or some shit
		if (lastLayer.getWhichLayerIsThis() == 2)
		{
			//stop
		} 
		else
		{
			Layer prevLayer = network.accessNetworkLayer(lastLayer.getWhichLayerIsThis() - 1);
			backpropagation(prevLayer, expectedValue);
		}
		//randomly subdivide data into mini-batches, and ayo just lost my train of thought
		
		return tempNegativeGradient;
	}
	
	public void setNewWeightsAndBiases(Network network)
	{
		//get the weight matrix from each layer and adjust the weight values
		//how tho^^
		//optimal learning rate = 0.01? 0.1?
		//new weight = old weight + (cost derivative)(learning rate)
		//new bias = old bias + (cost derivative)(learning rate)
		int index = 0;
		for (int i = 0; i < network.getNumberOfLayers(); i++)
		{
			index = (network.accessNetworkLayer(i)).setWeights(index, negativeGradient);
			
		}
		
	}
	
	public double calculateDerivativeWithRespectToWeight()
	{
		return 0;
	}
	
	public double calculateDerivativeWRespectToBias() 
	{
		return 0;
	}
	
	
}
