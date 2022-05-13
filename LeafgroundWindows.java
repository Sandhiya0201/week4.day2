package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LeafgroundWindows {
	public static void main(String[] args) throws InterruptedException {
		//set up the driver and create ChromeDriver Instance 	
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//navigate to the url
		driver.get("http://www.leafground.com/pages/Window.html");
		//Click button to open home page in New Window
		driver.findElement(By.id("home")).click();
		//get the title of the current page
		System.out.println("The current window title is " + driver.getTitle());
		//get the windows count
		System.out.println("The total number of open windows are " +  driver.getWindowHandles().size());
		//navigate to second window
		Set<String> windowHandles = driver.getWindowHandles();
		//System.out.println(windowHandles);
		List<String> windowHandleslist=new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandleslist.get(1));
		//get the titele of current page
		System.out.println("The current window title is " + driver.getTitle());
		driver.close();
		driver.switchTo().window(windowHandleslist.get(0));
		//get the title of current page
		System.out.println("The current window title is " + driver.getTitle());
		System.out.println("---------------------------------------");
		driver.findElement(By.xpath("//button[@onclick='openWindows()']")).click();
		System.out.println("The total number of open windows are " +  driver.getWindowHandles().size());
		System.out.println("-------------------------------------------------");
		driver.close();
		//To close all the child windows
		ChromeDriver driver1 = new ChromeDriver();
		driver1.manage().window().maximize();
		//navigate to the url
		driver1.get("http://www.leafground.com/pages/Window.html");
		driver1.findElement(By.id("color")).click();
		Thread.sleep(1000);
		List<String> windowHandleslist1=new ArrayList<String>(driver1.getWindowHandles());
		for (int i = driver1.getWindowHandles().size()-1; i >=1 ; i--) {
			driver1.switchTo().window(windowHandleslist1.get(i)).close();
		}
		driver1.switchTo().window(windowHandleslist1.get(0));
		System.out.println("The current title - "+driver1.getTitle()+" confirms that the main window is not closed");
		//click on the button and wait for 5 seconds to see there are more windows opened
		driver1.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		Thread.sleep(5000);
		List<String> windowHandleslist2=new ArrayList<String>(driver1.getWindowHandles());
		System.out.println("The number of windows opened after 5 seconds are : " +windowHandleslist2.size());
		driver1.close();
	}


}
