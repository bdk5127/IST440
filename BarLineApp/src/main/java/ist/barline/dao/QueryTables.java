package ist.barline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ist.barline.config.Config;
import ist.barline.input.UserInput;
import ist.barline.model.Bar;

public class QueryTables {
	
	public static void main(String[] args) {
		String barName = UserInput.getBarName();
		lengthSubmission(barName);
		getLength(barName);
	}

	public static Integer getLength(String bar) {
		AbstractApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
		int length = 0;
		int sum = 0;
		int average = 0;

		// Timestamp formatting and time selection, currently 2 hours prior to current
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		Timestamp tsCurrent = new Timestamp(new Date().getTime());
		cal.setTimeInMillis(tsCurrent.getTime());
		cal.add(Calendar.HOUR, -2);
		tsCurrent = new Timestamp(cal.getTime().getTime());

		System.out.println("Submissions After:  " + tsCurrent);

		Timestamp ts = null;
		ResultSet rs;
		PreparedStatement ps;
		Connection con;

		ArrayList<Bar> listBars = new ArrayList<Bar>();
		String sql = "SELECT * FROM bar_app." +bar;
		con = (Connection) container.getBean("connection");
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Bar newEntry = new Bar();
				newEntry.setTime(rs.getTimestamp("sub_time"));
				newEntry.setLength(rs.getInt("num_people"));
				
				ts = newEntry.getTime();
				// Computes Average AFTER A Certain Time
				if (ts.after(tsCurrent)) {
					length = newEntry.getLength();
					sum = sum + length;
					System.out.print(ts + "   ");
					System.out.println("sum: " + sum);
					listBars.add(newEntry);
				}

			}
			average = sum / listBars.size();
			System.out.println("average: " + average);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		container.close();
		return length;
	}

	public static void lengthSubmission(String bar) {
		AbstractApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
		PreparedStatement ps;
		Connection con;
		Scanner scanner = new Scanner(System.in);
		int length;
		
		System.out.print("Enter amount of people in line: ");
		length = scanner.nextInt();
		con = (Connection) container.getBean("connection");
		ArrayList<Bar> listBars = new ArrayList<Bar>();
		String sql = "INSERT INTO bar_app."+bar +" VALUES (current_timestamp()," + length + ")";
		
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			scanner.close();
			con.close();
			container.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
