/**
 * 
 */
package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;

import demo.testing;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Vikram
 *
 */

public class testing {

	public HashSet<String> met2() {

		File file = new File("C:\\tests\\MyLog.xml");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String a = sdf.format(file.lastModified());
		System.out.println(a);

		///////////////////////////////////////////////////////////////

		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;

		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler("C:\\tests\\MyLog.txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			SimpleDateFormat formatterw = new SimpleDateFormat();
			Date date = new Date(0);
			logger.info(formatterw.format(date.getTime()));

		} catch (SecurityException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		try {

			InputStream input = new FileInputStream("config.properties");

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);
			String url = prop.getProperty("db.url");
			String user = prop.getProperty("db.user");
			String passwd = prop.getProperty("db.password");
			String driver = prop.getProperty("db.driverclass");
			String acc_query = prop.getProperty("acc_query");
			String con_query = prop.getProperty("con_query");
			String add_query = prop.getProperty("add_query");

			Class.forName(driver);

			Connection con = DriverManager.getConnection(url, user, passwd);

			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(acc_query + " WHERE updated BETWEEN " + a + " and current_timestamp");
			ResultSetMetaData rsmd = rs1.getMetaData();
			int columnCount = rsmd.getColumnCount();
			ArrayList<String> accountResultList = new ArrayList<String>(columnCount);

			while (rs1.next()) {
				int i = 1;
				while (i <= columnCount) {
					accountResultList.add(rs1.getString(i++));
				}
			}

			// System.out.println("accountList : " + accountResultList);// 4 times

			Statement st2 = con.createStatement();
			// st2.setFetchSize(1000);
			ResultSet rs2 = st2.executeQuery(con_query + " WHERE updated BETWEEN " + a + " and current_timestamp");
			ResultSetMetaData rsmd1 = rs2.getMetaData();
			int numColumns = rsmd1.getColumnCount();

			ArrayList<String> contactResultList = new ArrayList<String>(numColumns);

			while (rs2.next()) {
				int j = 1;
				while (j <= numColumns) {
					contactResultList.add(rs2.getString(j++));

				}

			}
			// System.out.println("contactList : " + contactResultList);// 4 times

			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(add_query + " WHERE updated BETWEEN " + a + " and current_timestamp");
			ResultSetMetaData rsmd2 = rs3.getMetaData();
			ArrayList<String> addressResultList = new ArrayList<String>(numColumns);

			int numColumns1 = rsmd2.getColumnCount();
			while (rs3.next()) {
				int k = 1;
				while (k <= numColumns) {
					addressResultList.add(rs3.getString(k++));
				}

			}
			// System.out.println("addressList : " + addressResultList);
			ArrayList<String> FinalResultList = new ArrayList<String>();
			FinalResultList.addAll(accountResultList);
			FinalResultList.addAll(contactResultList);
			FinalResultList.addAll(addressResultList);

			// System.out.println("FinalList : " + FinalResultList);
			HashSet<String> set = new HashSet<String>(FinalResultList);
			System.out.println("FinalSet : " + set);
			int LengthSet;
			LengthSet = set.size();

			rs2.close();

			rs1.close();
			con.close();
			return set;

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
