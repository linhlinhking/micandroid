package ningbo.media.comparator;

import java.util.Comparator;
import java.util.Map;

import ningbo.media.bean.Location;

public class ObjectComparator {

	
	public static class DistanceComparator implements
			Comparator<Object> {
		@SuppressWarnings("unchecked")
		
		public int compare(Object o1, Object o2) {
			Map.Entry<Location, Double> map1 = (Map.Entry<Location, Double>) o1;
			Map.Entry<Location, Double> map2 = (Map.Entry<Location, Double>) o2;

			return map1.getValue().compareTo(map2.getValue());

		}
	}

}
