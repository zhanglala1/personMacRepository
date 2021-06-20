package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class junitTest {
	
	public static void main(String[] args) {
		
		Assert.assertEquals("ok", 1,1);
		
		assertEquals("ok", 1,1);
		
		Assert.assertSame(1, 2);
		
		
	}

}
