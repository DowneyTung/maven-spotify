package com.spotify;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.Properties;

import org.testng.annotations.*;
import org.testng.Assert;

public class InvalidLoginTest {
	Path currentRelativePath = Paths.get("");
	String s_path = currentRelativePath.toAbsolutePath().toString();
    Runtime rt = Runtime.getRuntime();
    Screen s = new Screen();

	@BeforeTest
	public void setup() throws IOException{
	    Process pr = rt.exec("open -a Spotify");
	}
	
	@Test
	public void invalidLogin() throws FindFailed, InterruptedException, IOException{
	    Properties prop = new Properties();
	    InputStream input = null;

	    input = new FileInputStream(s_path + "/src/test/java/config.properties");
	    prop.load(input);
	    String username = prop.getProperty("username");
	    String invalid_password = prop.getProperty("invalid_password");
//	    Pattern login_button = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_button.png");	    
//	    s.doubleClick(s.wait(login_button, 10));
	//    
	    Pattern email_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/email_field.png");	    
	    s.type(username);
	    Pattern password_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/password_field.png");	    
	    s.doubleClick(s.wait(password_field, 10));
	    s.type(invalid_password);

	    Pattern login_button_green = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_button_green.png");	    
	    s.doubleClick(s.wait(login_button_green, 10));
	    
	    Pattern login_error_msg = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_error_msg.png");	    
	    s.wait(login_error_msg, 10);
	    Assert.assertTrue(login_error_msg != null);
	}
	
	@AfterTest
	public void tearDown() throws IOException{
	    Process close = rt.exec("killall Spotify");
	}



}
