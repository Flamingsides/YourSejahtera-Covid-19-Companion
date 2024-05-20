
import java.util.ArrayList;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class StateDatasets 
{
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	
	public ArrayList<HashMap<String, String>> getInformation(String start_date, String end_date) throws Exception
	{
		APIAccessProvider access = APIAccessProvider.getCovidDataset();
		return access.getInformation(start_date, end_date);
	}
	
	public StateDatasets()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		Date end_date = cal.getTime();
		cal.add(Calendar.DATE, -6);
		Date start_date = cal.getTime();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start_str = dateFormat.format(start_date);
		String end_str = dateFormat.format(end_date);
		
	    try 
	    {
	    	list = getInformation(start_str, end_str);
	    } 
	    catch (Exception e)
	    {
	    	System.out.println("Please Try again in 5 minutes");
	    }	    
	}
	public CategoryDataset MalaysiaDataset() throws Exception {
		DefaultCategoryDataset Malaysia = new DefaultCategoryDataset();
		String MAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Malaysia")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Malaysia.addValue(ca, MAC, cases.get("date"));
			}
		}
		
		return Malaysia;
	}
	public CategoryDataset JohorDataset() throws Exception {
		DefaultCategoryDataset Johor = new DefaultCategoryDataset();
		String JAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Johor")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Johor.addValue(ca, JAC, cases.get("date"));
			}
		}
		
        return Johor;
	}
	public CategoryDataset KedahDataset() throws Exception {
		DefaultCategoryDataset Kedah = new DefaultCategoryDataset();
		String KAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Kedah")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Kedah.addValue(ca, KAC, cases.get("date"));
			}
		}
		
		return Kedah;
	}
	public CategoryDataset KelantanDataset() throws Exception {
		DefaultCategoryDataset Kelantan = new DefaultCategoryDataset();
		String KeAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Kelantan")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Kelantan.addValue(ca, KeAC, cases.get("date"));
			}
		}
		
		return Kelantan;
	}
	public CategoryDataset MelakaDataset() throws Exception {
		DefaultCategoryDataset Melaka = new DefaultCategoryDataset();
		String MAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Melaka")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Melaka.addValue(ca, MAC, cases.get("date"));
			}
		}
		
		return Melaka;
	}
	public CategoryDataset NSDataset() throws Exception {
		DefaultCategoryDataset NS = new DefaultCategoryDataset();
		String NSAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Negeri Sembilan")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			NS.addValue(ca, NSAC, cases.get("date"));
			}
		}
		
		return NS;
	}
	public CategoryDataset PahangDataset() throws Exception {
		DefaultCategoryDataset Pahang = new DefaultCategoryDataset();
		String PAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Pahang")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Pahang.addValue(ca, PAC, cases.get("date"));
			}
		}
		
		return Pahang;
	}
	public CategoryDataset PerakDataset() throws Exception {
		DefaultCategoryDataset Perak = new DefaultCategoryDataset();
		String PerAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Pahang")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Perak.addValue(ca, PerAC, cases.get("date"));
			}
		}
		
		return Perak;
	}
	public CategoryDataset PerlisDataset() throws Exception {
		DefaultCategoryDataset Perlis = new DefaultCategoryDataset();
		String PeAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Perlis")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Perlis.addValue(ca, PeAC, cases.get("date"));
			}
		}
		
		return Perlis;
	}
	public CategoryDataset PPDataset() throws Exception {
		DefaultCategoryDataset PP = new DefaultCategoryDataset();
		String PPAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Pulau Pinang")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			PP.addValue(ca, PPAC, cases.get("date"));
			}
		}
		
		return PP;
	}
	public CategoryDataset SabahDataset() throws Exception {
		DefaultCategoryDataset Sabah = new DefaultCategoryDataset();
		String SaAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Sabah")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Sabah.addValue(ca, SaAC, cases.get("date"));
			}
		}
		
		return Sabah;
	}
	public CategoryDataset SarawakDataset() throws Exception {
		DefaultCategoryDataset Sarawak = new DefaultCategoryDataset();
		String SarAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Sarawak")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Sarawak.addValue(ca, SarAC, cases.get("date"));
			}
		}
		
		return Sarawak;
	}
	
	public CategoryDataset SelangorDataset() throws Exception {
		DefaultCategoryDataset Selangor = new DefaultCategoryDataset();
		String SAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Selangor")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Selangor.addValue(ca, SAC, cases.get("date"));
			}
		}
		
		return Selangor;
	}
	public CategoryDataset TerrenganuDataset() throws Exception {
		DefaultCategoryDataset Terrenganu = new DefaultCategoryDataset();
		String TAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("Terrenganu")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Terrenganu.addValue(ca, TAC, cases.get("date"));
			}
		}
		
		return Terrenganu;
	}
	public CategoryDataset KLDataset() throws Exception {
		DefaultCategoryDataset KL = new DefaultCategoryDataset();
		String KLAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("W.P. Kuala Lumpur")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			KL.addValue(ca, KLAC, cases.get("date"));
			}
		}
		
        return KL;
	}
	public CategoryDataset LabuanDataset() throws Exception {
		DefaultCategoryDataset Labuan = new DefaultCategoryDataset();
		String LAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("W.P. Labuan")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			Labuan.addValue(ca, LAC, cases.get("date"));
			}
		}
		
        return Labuan;
	}
	public CategoryDataset PJDataset() throws Exception {
		DefaultCategoryDataset PJ = new DefaultCategoryDataset();
		String PJAC = "Active Cases";
		for(HashMap<String,String> cases : list)
		{
			if(cases.containsValue("W.P. Putrajaya")) {
			int ca = Integer.parseInt(cases.get("cases_active"));
			PJ.addValue(ca, PJAC, cases.get("date"));
			}
		}
		
        return PJ;
	}

}
