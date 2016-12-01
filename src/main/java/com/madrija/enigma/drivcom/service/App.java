package com.madrija.enigma.drivcom.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;


import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.UID;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Connection;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.IncompatibleConnectionException;
import org.dcm4che3.net.Priority;
import org.dcm4che3.net.pdu.AAssociateRQ;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.tool.storescu.StoreSCU;

public class App {
	
	private Device device = new Device("ascu");
	private static ApplicationEntity ae = new ApplicationEntity("aSCU");
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
	
	private static String[] IVR_LE_FIRST = { UID.ImplicitVRLittleEndian, UID.ExplicitVRLittleEndian,
			UID.ExplicitVRBigEndianRetired };

	public App() throws IOException {
		device.addConnection(conn);
		device.addApplicationEntity(ae);
		ae.addConnection(conn);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("¡¡¡¡Bienvenido a DrivCOM!!!!");
		System.out.println("1. Store SCP.");
		
		int op = sc.nextInt();
		if(op == 1){
			send();
			
		}
		
	}
	
	private static void send() throws IOException {
		String[] args = {"-c SUT_AE@localhost:104","C:\\Users\\Mountain\\git\\DrivCOM\\data\\IM-0001-0001.dcm"};
		StoreSCU.main(args);
	}

	private static void storeSCP() throws IOException {
		App a = new App();
		a.getRq().setCalledAET("SUT_AE");
		a.getRemote().setHostname("localhost");
		a.getRemote().setPort(104);
		
		a.getAe().setAETitle("SUT_AE");
		
		// General configure
		a.getConn().setReceivePDULength(Connection.DEF_MAX_PDU_LENGTH);
		a.getConn().setSendPDULength(Connection.DEF_MAX_PDU_LENGTH);
		a.getConn().setMaxOpsInvoked(0);
		a.getConn().setMaxOpsPerformed(0);
		a.getConn().setConnectTimeout(0);
		a.getConn().setRequestTimeout(0);
		a.getConn().setAcceptTimeout(0);
		a.getConn().setReleaseTimeout(0);
		a.getConn().setResponseTimeout(0);
		a.getConn().setRetrieveTimeout(0);
		a.getConn().setIdleTimeout(0);
		a.getConn().setSocketCloseDelay(Connection.DEF_SOCKETDELAY);
		a.getConn().setSendBufferSize(0);
		a.getConn().setReceiveBufferSize(0);
		
		// Configure service class
		// Establece el contexto de presentación:
		// modelo de información, la sintaxis de transferencia
		a.cuid = UID.ModalityWorklistInformationModelFIND;
		// UID.ModalityWorklistInformationModelFIND
		// UID.StudyRootQueryRetrieveInformationModelFIND

		a.getRq().addPresentationContext(new PresentationContext(1, a.cuid, IVR_LE_FIRST));

		try {
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			a.device.setExecutor(executorService);
			a.device.setScheduledExecutor(scheduledExecutorService);
			try {
				a.open();
				//a.send();

			} finally {
				a.close();
				executorService.shutdown();
				scheduledExecutorService.shutdown();
			}
		} catch (Exception e) {
			System.err.println("findscu: " + e.getMessage());
			e.printStackTrace();
		}
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
