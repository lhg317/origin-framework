import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StopWatch;

public class SpelTest {

	public static void main1(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
//		Expression exp = parser.parseExpression("'Hello World'");
//		String message = (String) exp.getValue();
//		System.out.println(message);
		
		RootObject root = new RootObject();
		root.put("a", 1);
		root.put("b", 2);
		root.put("str", "hello");
		root.put("obj", new Demo("LHG"));
		EvaluationContext context = new StandardEvaluationContext(root);
		StopWatch stopWatch  = new StopWatch();
		stopWatch.start();
		Expression exp = parser.parseExpression("valueMap['str'] + (valueMap['a'] + valueMap['b']) +' ' + valueMap['obj'].message");
		System.out.println(exp.getValue(context));
		stopWatch.stop();
		stopWatch.start();
		exp = parser.parseExpression("(valueMap['a'] - valueMap['b']) +' ' + valueMap['obj'].message");
		System.out.println(exp.getValue(context));
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}
}

class RootObject {
	private Map<String,Object> valueMap = new HashMap<>();

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}
	
	public void put(String name,Object value){
		valueMap.put(name, value);
	}
}

class Demo{
	private String message;

	public Demo() {}
	
	public Demo(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}