package com.madrija.enigma.drivcom.service;

import java.util.Scanner;

import org.dcm4che3.tool.storescp.StoreSCP;
import org.dcm4che3.tool.storescu.StoreSCU;

public class App {

	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		int op = 1;
		while(op>0 && op<3){
			System.out.println("¡¡¡¡Bienvenido a DrivCOM!!!!");
			System.out.println("1. Store SCP.");
			System.out.println("2. Store SCU.");
			System.out.println("3. Print SCP.");
			System.out.println("0. Salir.");
			System.out.print("Opción: ");
			op = sc.nextInt();
			switch(op){
			case 1:
				System.out.print("STORE SCP: ");
				String[] argsSCU = {"-c prueba@localhost:104","C:\\Users\\Mountain\\git\\DrivCOM\\data\\d1I00001.dcm"};	
				StoreSCU.main(argsSCU);
			break;
			case 2:
				String[] argsSCP = {"-b prueba@localhost:104"};
				StoreSCP.main(argsSCP);
			break;
			case 3:
				
			break;
			}
			
		}
		System.out.println("¡¡¡¡¡¡ADIOS!!!!!!");
	}

}
