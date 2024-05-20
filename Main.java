public class Main
{
    private static final int F_WIDTH = 1000;
	private static final int F_HEIGHT = 650;

	// App object is accessible to other pages
	public static App app;

	public static void main(String[]args) throws Exception
    {
    	app = new App(F_WIDTH, F_HEIGHT);
    	app.start();
    }
}
