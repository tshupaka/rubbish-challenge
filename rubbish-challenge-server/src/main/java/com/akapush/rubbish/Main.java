package com.akapush.rubbish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

		Integer result = list.stream().reduce(1, (sum, x) -> sum * x, (x1, x2) -> 0);
		System.out.println(result);
		
		
		/*list.stream().collec(()-> new HashMap<Integer, Integer>(), (Map<Integer,Integer> map, Integer value)-> 
		map.put(value%2, map.get(value%2)), (Map<Integer,Integer> x1, Map<Integer,Integer> x2) -> x1);*/
		
		Function<Integer, String> key = String::valueOf;
		Function<Integer, Integer> value = ( x) ->  x*x;
		
		 list.stream().collect(Collectors.toMap(key,value)).forEach((x,y)->System.out.println(x+" : " + y));
	}

}
