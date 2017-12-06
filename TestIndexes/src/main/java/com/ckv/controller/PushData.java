package com.ckv.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.fluttercode.datafactory.impl.DataFactory;

public class PushData {

	public static final String PATH = "E:/datauser.txt";
	public static final String URL = "jdbc:mysql://localhost:3306/test";
	private DataFactory data;
	private ConnectionDB con;

	public PushData() {
		data = new DataFactory();
		con = new ConnectionDB(URL, "root", "12345678");
	}

	public void createData() {

		try {
			File f = new File("E:/datauser.txt");
			FileUtils.touch(f);
			List<String> l = new ArrayList<String>();

			SimpleDateFormat fd = new SimpleDateFormat("yyyy-mm-dd");
			for (int i = 1; i <= 1000000; i++) {
				String name = data.getFirstName() + "\t" + data.getLastName();
				String sex = data.getItem(Arrays.asList("Male", "Female"));

				Date doB = data.getBirthDate();
				String dOB = fd.format(doB);
				String country = data.getCity();

				String rs = "/N\t" + name + "\t" + sex + "\t" + dOB + "\t" + country + "\t";
				l.add(rs);
				if (i % 1000 == 0) {
					FileUtils.writeLines(f, l, true);
					l.clear();
				}
				System.out.println(i / 10000f + " %");
			}

			System.out.println("Create sucessfull!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void pushDataFromFiletoTable(String nametable) {
		long time = con.pushDatabaseFromFileToTable(nametable, PATH);
		System.out.println("Push data to " + nametable + " in (" + new BigDecimal(time / 1000f) + "sec )");
	}


}
