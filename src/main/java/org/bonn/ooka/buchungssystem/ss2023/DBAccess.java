package org.bonn.ooka.buchungssystem.ss2023;


import org.bonn.ooka.buchungssystem.ss2023.models.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

class DBAccess {
	
	protected final static int HOTEL = 0;
	protected final static int AUTO = 1;
	private String url = "jdbc:postgresql://dumbo.inf.h-brs.de/demouser";
	private Connection conn;
	
	protected DBAccess() { }

//	protected static void main(String[] args) {
//		DBAccess acc = new DBAccess();
//		System.out.println("Mini-Tutorial der Klasse DBAccess" );
//		System.out.println("c/o Sascha Alda, 2019 - 2023" );
//		System.out.println("---------------------------------" );
//		System.out.println("Zunächst MUSS ein externer Client (außerhalb der Komponente!) mit der Methode openConnection() die Session explizit öffnen!" );
//		acc.openConnection();
//
//		System.out.println("\nSuche nach allen Hotels:" );
//		System.out.println("Methodenaufruf: getObjects( DBAccess.HOTEL, \"*\")"   );
//		List<Hotel> result = acc.getObjects(DBAccess.HOTEL, "*");
//		for (Hotel hotel: result){
//			System.out.println( "Hotel: " + hotel.toString());
//		}
//
//		System.out.println("\nSuche nach Hotels mit dem TeilString 'Jahres':" );
//		System.out.println("Methodenaufruf: getObjects( DBAccess.HOTEL, \"Jahres\")"   );
//		result = acc.getObjects(DBAccess.HOTEL, "Jahres");
//		for (Hotel hotel: result){
//			System.out.println( "Hotel: " + hotel.toString());
//		}
//
//		System.out.println("\nDann MUSS ein externer Client mit der Methode closeConnection() die Session explizit schließen!" );
//		acc.closeConnection();
//	}
	
	protected void openConnection(){
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	Properties props = new Properties();
	  	props.setProperty("user","demouser");
	  	props.setProperty("password","demouser");
	  	try {
			 this.conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected List<Hotel> getObjects(int type, String value){
		Statement st;
		ResultSet rs;
		List<Hotel> result = new ArrayList<>();
		if (value.equals("*") ) {
			value = "";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM buchungsystem.hotel WHERE buchungsystem.hotel.name ilike " + "'%" + value +  "%'" );
			while (rs.next() ){
				Hotel hotel = new Hotel(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("ort"),		// ort is mapped to location
						rs.getInt("sterne"),		// sterne is mapped to stars
						rs.getString("kontact"));// kontact gets mapped to contact
				result.add(hotel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	protected List<Hotel> getHotelsByContact(int type, String name, String contact){
		Statement st;
		ResultSet rs;
		List<Hotel> result = new ArrayList<>();
		if (name.equals("*") ) {
			name = "";
		}
		if (contact.equals("*") ) {
			contact = "";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM buchungsystem.hotel h WHERE h.kontact ilike '%" + contact +  "%' AND h.name ilike '%" + name + "%'" );
			while (rs.next() ){
				Hotel hotel = new Hotel(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("ort"),		// ort is mapped to location
						rs.getInt("sterne"),		// sterne is mapped to stars
						rs.getString("kontact"));// kontact gets mapped to contact
				result.add(hotel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	protected void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
