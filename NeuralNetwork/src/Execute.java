import java.util.ArrayList;

public class Execute {
	
	public void runNeuralNetwork(Network network) 
	{
		int[] labels = mnistreader.readLabels("Path to unzipped minst label file");
		int[][] images = mnistreader.readImages("Path to unzipped mnist images file");
		
		for (int i = 0; i < images[0].length; i++)
		{
			for (int j = 0; j < images.length; j++)
			{
				((network.accessNetworkLayer(0)).getNode(i+j)).setActivationValue(images[i][j]);
			}
			
		}	
	}

	public static void main(String[] args) 
	{
		
	}
}
