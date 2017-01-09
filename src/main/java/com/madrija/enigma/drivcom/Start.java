package com.madrija.enigma.drivcom;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Connection;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.IncompatibleConnectionException;
import org.dcm4che3.net.Priority;
import org.dcm4che3.net.pdu.AAssociateRQ;

import com.madrija.enigma.drivcom.service.StoreSCPReceive;
import com.madrija.enigma.drivcom.service.StoreSCUSend;


public class Start {
	
	private Device device = new Device("ascu");
	private ApplicationEntity ae = new ApplicationEntity("aSCU");
	private final Connection conn = new Connection();
	private final Connection remote = new Connection();
	private final AAssociateRQ rq = new AAssociateRQ();
	private int priority = Priority.NORMAL;
	private int cancelAfter;
	private String cuid;
	private String iuid;
	private String level;
	private Attributes keys = new Attributes();
	private Association as;
	private AtomicInteger totNumMatches = new AtomicInteger();
	
	static Scanner sc = new Scanner(System.in);
	
	public Start() throws IOException {
		device.addConnection(conn);
		device.addApplicationEntity(ae);
		ae.addConnection(conn);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("¡¡¡¡Bienvenido a DrivCOM!!!!");
		System.out.println("1. Store SCU.");
		System.out.println("2. Store SCP.");
		
		int op = sc.nextInt();
		if(op == 1){
			send();
			
		}
		if(op == 2){
			receive();
		}
		
	}
	
	private static void receive() {
		StoreSCPReceive scp = new StoreSCPReceive();
		System.out.println("****************************************************");
		System.out.println("********************  STORESCP  ********************");
		System.out.println("****************************************************");
		System.out.print("Introduce el AETitle: ");
		scp.setAet(sc.next());
		System.out.print("Introduce el puerto: ");
		scp.setPort(sc.nextInt());
		if(scp.startReceive())
			System.out.println("Se ha enviado correctamente el archivo");
		else
			System.out.println("Ha habido algún error al enviar el archivo");
		
	}

	private static void send() throws IOException {
		StoreSCUSend scu = new StoreSCUSend();
		System.out.println("****************************************************");
		System.out.println("********************  STORESCU  ********************");
		System.out.println("****************************************************");
		System.out.print("Introduce el AETitle: ");
		scu.setAet(sc.next());
		System.out.print("Introduce el Host: ");
		scu.setHost(sc.next());
		System.out.print("Introduce el puerto: ");
		scu.setPort(sc.nextInt());
		System.out.print("Introduce la ruta del archivo: ");
		scu.setPath(sc.next());
		if(scu.startSend())
			System.out.println("Se ha enviado correctamente el archivo");
		else
			System.out.println("Ha habido algún error al enviar el archivo");
		
		//String[] args2={"-b STORESCP:11112"};
		//StoreSCP.main(args2);
	}

	public void open()
			throws IOException, InterruptedException, IncompatibleConnectionException, GeneralSecurityException {
		as = ae.connect(remote, rq);
	}

	public void close() throws IOException, InterruptedException {
		if (as != null && as.isReadyForDataTransfer()) {
			as.waitForOutstandingRSP();
			as.release();
		}
	}
	
	public List<List<String>> readData() {
	       
        return null;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public ApplicationEntity getAe() {
		return ae;
	}

	public void setAe(ApplicationEntity ae) {
		this.ae = ae;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getCancelAfter() {
		return cancelAfter;
	}

	public void setCancelAfter(int cancelAfter) {
		this.cancelAfter = cancelAfter;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Attributes getKeys() {
		return keys;
	}

	public void setKeys(Attributes keys) {
		this.keys = keys;
	}

	public Association getAs() {
		return as;
	}

	public void setAs(Association as) {
		this.as = as;
	}

	public AtomicInteger getTotNumMatches() {
		return totNumMatches;
	}

	public void setTotNumMatches(AtomicInteger totNumMatches) {
		this.totNumMatches = totNumMatches;
	}

	public Connection getConn() {
		return conn;
	}

	public Connection getRemote() {
		return remote;
	}

	public AAssociateRQ getRq() {
		return rq;
	}

	public String getIuid() {
		return iuid;
	}

	public void setIuid(String iuid) {
		this.iuid = iuid;
	}
	
}
