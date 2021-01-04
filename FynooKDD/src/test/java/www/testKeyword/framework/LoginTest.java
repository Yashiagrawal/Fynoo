package www.testKeyword.framework;

import org.testng.annotations.Test;

import www.keword.engine.Keywordengine;

public class LoginTest {
	
	public Keywordengine keywordengine;

	@Test
	public void login() {
		keywordengine = new Keywordengine();
		keywordengine.startExecution("logincase");

	}

}
