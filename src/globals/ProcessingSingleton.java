package globals;

public class ProcessingSingleton {
	// "static" IS THE KEY WORD HERE
    private static ProcessingSingleton P5INSTANCE = new ProcessingSingleton();

    private ProcessingSingleton() {}

    public static ProcessingSingleton getInstance() {
        return P5INSTANCE;
    }

    //--------
    
    private Main appMain;

    public void setProcessingSingleton(Main _appMain){
    	this.appMain = _appMain;
	}

    public Main getProcessingSingleton() {
		return appMain;
	}
}