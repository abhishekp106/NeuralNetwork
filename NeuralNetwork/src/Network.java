import java.util.ArrayList;

public class Network {
	public ArrayList<Layer> network = new ArrayList<Layer>();
	
	public Network()
	{
		network.add(new Layer(1, 784, 0));
		network.add(new Layer(2, 16, 784));
		network.add(new Layer(3, 16, 16));
		network.add(new Layer(4, 10, 16));
	}
	
	public Layer accessNetworkLayer(int whichLayer)
	{
		return network.get(whichLayer);
	}
	
	public int getNumberOfLayers()
	{
		return network.size();
	}

}
