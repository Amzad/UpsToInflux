import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Program {
	
	public static void main(String[] args) {
		/*
		UPSNAME  : ups0
		STATUS   : ONLINE
		LINEV    : 123.0 Volts
		LOADPCT  :  24.0 Percent Load Capacity
		BCHARGE  : 100.0 Percent
		TIMELEFT : 144.5 Minutes
		BATTV    : 26.8 Volts
		TONBATT  : 0 seconds
		NOMBATTV :  24.0
		*/
		
		String location = "C:\\apcupsd\\bin\\apcaccess";
		String program = "apcaccess.exe";
		List<String> list = new ArrayList<String>();
		list.add("apcaccess status");
		
		String upsStatus, lineVoltage, currentLoad,  batteryCharge, timeLeft, batteryVoltage, secondsOnBattery;
		
		ProcessBuilder pb = new ProcessBuilder(location, "status");
		pb.redirectErrorStream(true);
		
		try {
			Process pr = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("STATUS")) { 
					upsStatus = line.substring(line.indexOf(":")+1, line.length());
					upsStatus = upsStatus.trim();
					System.out.println(upsStatus);
				}
				
				if (line.startsWith("LINEV")) {
					lineVoltage = line.substring(line.indexOf(":")+1, line.indexOf("Volts")-1);
					lineVoltage = lineVoltage.trim();
					System.out.println(lineVoltage);
				}
				
				if (line.startsWith("LOADPCT")) {
					currentLoad = line.substring(line.indexOf(":")+1, line.indexOf("Percent")-1);
					currentLoad = currentLoad.trim();	
					System.out.println(currentLoad);
				}
				
				if (line.startsWith("BCHARGE")) {
					batteryCharge = line.substring(line.indexOf(":")+1, line.indexOf("Percent")-1);
					batteryCharge = batteryCharge.trim();
					System.out.println(batteryCharge);
				}
				
				if (line.startsWith("TIMELEFT")) {
					timeLeft = line.substring(line.indexOf(":")+1, line.indexOf("Minutes")-1);
					timeLeft = timeLeft.trim();
					System.out.println(timeLeft);
				}
				
				if (line.startsWith("BATTV")) {
					batteryVoltage = line.substring(line.indexOf(":")+1, line.indexOf("Volts")-1);
					batteryVoltage = batteryVoltage.trim();
					System.out.println(batteryVoltage);
				}
				
				if (line.startsWith("TONBATT")) {
					secondsOnBattery = line.substring(line.indexOf(":")+1, line.indexOf("Seconds")-1);
					secondsOnBattery = secondsOnBattery.trim();
					System.out.println(secondsOnBattery);
				}
				
				//System.out.println(line);
			}
			pr.waitFor();
			br.close();

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
