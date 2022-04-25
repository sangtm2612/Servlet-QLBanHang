package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TC1 {
	private WebDriver driver;
	
  @Test
  public void Test1(int dp) {
	  driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]")).sendKeys("3");
	  driver.findElement(By.xpath("//button[contains(text(),'Mua hàng')]")).click();
	  assertEquals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]")).getText(), "Thêm sản phẩm vào giỏ hàng thành công");
  }
  
  @Test(dataProvider = "dp", expectedExceptions = {IllegalArgumentException.class, NullPointerException.class}) 
  public void Test2(String dp) {
	  try {
		  driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]")).sendKeys(dp);
		  driver.findElement(By.xpath("//button[contains(text(),'Mua hàng')]")).click();
		  assertEquals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]")).getText(), "Ngoại lệ");
	} catch (Exception e) {
		e.printStackTrace();
	}
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "0"},
      new Object[] { "-1"},
      new Object[] { ""},
    };
  }
  
  @BeforeTest
  public void beforeTest() {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.get("http://localhost:8080/SANGTM_PH17730_ASM/home/product");
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
