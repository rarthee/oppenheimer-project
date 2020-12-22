package stepDefinition;




import base.BaseUtil;
import io.cucumber.java.*;


public class SetupTeardown extends BaseUtil{
	
	private BaseUtil base;

    public SetupTeardown(BaseUtil base) {
        this.base = base;
    }
    public SetupTeardown() {
      
    }

    @Before
    public void InitializeTest(Scenario scenario) {


        base.scenariodef = base.features.createNode(scenario.getName());
        
        System.out.println("Opening the browser : Chrome");


		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sahana Rangarajan\\Documents\\Arthee\\chromedriver.exe");
       // base.driver = new ChromeDriver();
    }


    @After
    public void TearDownTest(Scenario scenario) {
        System.out.println("Closing the browser : Chrome");
     //   driver.quit();
       
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
      //  System.out.println("Before every step " + scenario.getId());

      
    }
    

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
    	
    
    	
        
    }

   	
 
}
