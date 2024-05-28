package webclient.modules.bp;

import java.io.File;

import webclient.general.BaseDocList;
import webuita.locating.Locators;

public class ActivityList extends BaseDocList{
	private Locators loc;
	public ActivityList(){
		super(); 
		loc = new Locators("Activity" + File.separator + "ActivityList_Mapping.xml", log);
		
	}
}
