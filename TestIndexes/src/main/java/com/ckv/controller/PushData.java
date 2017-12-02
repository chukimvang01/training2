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

	private DataFactory data;

	public PushData() {
		data = new DataFactory();
	}

	@SuppressWarnings("deprecation")
	public void createData() {

		try {
			File f = new File("E:/datauser.txt");
			FileUtils.touch(f);
			List<String> l = new ArrayList<String>();

			SimpleDateFormat fd = new SimpleDateFormat("yyyy-mm-dd");
			Date maxDate = new Date();
			for (int i = 1; i <= 1000000; i++) {
				String name = data.getFirstName() + "\t" + data.getLastName();
				String sex = data.getItem(Arrays.asList("Male", "Female"));

				Date doB = data.getBirthDate();
				String dOB = fd.format(doB);
				String country = data.getCity();

				String rs = "/N\t" + name + "\t" + sex + "\t" + dOB + "\t" + country;
				l.add(rs);
				if (i % 1000 == 0) {
					FileUtils.writeLines(f, l, true);
					l.clear();
				}
				System.out.println(i/10000f +" %");
			}

			System.out.println("Create sucessfull!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		//new PushData().createData();
		String path = "E:/datauser.txt";
		String url = "jdbc:mysql://localhost:3306/test";
		ConnectionDB con=new ConnectionDB(url, "root", "12345678");
		long timeCreateDataNoIndex = con.pushDatabaseFromFileToTableNoIndex(path);
		//long timeCreateDataHasIndex = con.pushDatabaseFromFileToTableHasIndex(path);
		System.out.println(new BigDecimal(timeCreateDataNoIndex/1000f)+" sec");
		//System.out.println(new BigDecimal(timeCreateDataHasIndex/1000f)+" sec");
	}

}
