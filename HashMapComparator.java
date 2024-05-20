import java.util.Comparator;
import java.util.HashMap;

public class HashMapComparator implements Comparator<HashMap<String, String>>
	{
		private final String key;
		
		public HashMapComparator(String key)
		{
			this.key = key;
		}
		
		public int compare(HashMap<String, String> dataset1, HashMap<String, String> dataset2)
		{
			String data1 = dataset1.get(key);
			String data2 = dataset2.get(key);
			return data1.compareTo(data2);
		}
		
		
	}