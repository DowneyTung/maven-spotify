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
import org.sikuli.script.IScreen;
import org.sikuli.script.ScreenImage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.Properties;

import org.testng.annotations.*;
import org.testng.Assert;

public class PlayTrackTest {

	Path currentRelativePath = Paths.get("");
	String s_path = currentRelativePath.toAbsolutePath().toString();
    Runtime rt = Runtime.getRuntime();
    Screen s = new Screen();
    
    @BeforeTest
    public void setup() throws IOException{
	    Process pr = rt.exec("open -a Spotify");
    }
    
    @Test
    public void playTrackTest() throws FindFailed, InterruptedException, IOException {
		Properties prop = new Properties();
	    InputStream input = null;
	    
	    input = new FileInputStream(s_path + "/src/test/java/config.properties");
	    prop.load(input);
	    String username = prop.getProperty("username");
	    String password = prop.getProperty("password");
	    		
//	    Pattern login_button = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_button.png");	    
//	    s.doubleClick(s.wait(login_button, 10)); 
	    Pattern email_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/email_field.png");	    
	    s.type(username);
	    Pattern password_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/password_field.png");	    
	    s.doubleClick(s.wait(password_field, 10));
	    s.type(password);

	    Pattern login_button_green = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_button_green.png");	    
	    s.doubleClick(s.wait(login_button_green, 10));
	    
	    Pattern search_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/search_field.png");	    
	    s.wait(search_field, 10);
	    Assert.assertTrue(search_field != null);
  
	    s.doubleClick(s.wait(search_field, 10));
	    s.type("charlie puth");
	    
	    Pattern charlie_puth_icon = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/charlie_puth_icon.png");	    
	    s.wait(charlie_puth_icon, 10);
	    Assert.assertTrue(charlie_puth_icon != null);
	    s.doubleClick(s.wait(charlie_puth_icon, 10));
	    
	    Thread.sleep(2000);
	    Pattern pause_button = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/pause_button.png");
	    s.wait(pause_button, 10);
	    Assert.assertTrue(pause_button != null);
	    s.click(s.wait(pause_button, 10));
	    
	    Pattern search_cancel_icon = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/search_cancel_icon.png");	        
	    s.doubleClick(s.wait(search_cancel_icon, 10));
	    Thread.sleep(2000);
	   	     		
	    
	    Pattern play_button = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/play_button.png");
	    s.wait(play_button, 10);
	    Assert.assertTrue(play_button != null);
	    s.click(s.wait(play_button, 10));  
	    Thread.sleep(2000);
	    
    }
    
    @AfterTest
    public void tearDown() throws IOException{
	    Process close = rt.exec("killall Spotify");
    }
}
