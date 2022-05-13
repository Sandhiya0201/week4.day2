package week4.day2;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	public static void main(String[]args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver =new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.name("PASSWORD")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif'][1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleslist=new ArrayList<String>(windowHandles);
		String secondwindow = windowHandleslist.get(1);
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']//tr[1]//td[1]//a")).click();	
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		Set<String> windows1 = driver.getWindowHandles();
		List<String> windows=new ArrayList<String>(windows1);
		driver.switchTo().window(windows.get(1));
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]//tr[1]//td[1]//a")).click();
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println(driver.getTitle());
		if(driver.getTitle().contains("View Contact")) {
			System.out.println("The merge of two contacts was successful");
		}
		
	}

}
