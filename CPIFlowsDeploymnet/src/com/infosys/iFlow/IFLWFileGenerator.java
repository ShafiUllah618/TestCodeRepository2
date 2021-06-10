package com.infosys.iFlow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;  
public class IFLWFileGenerator {
	
	public static void main(String[] args) {
		
		String SenderName = "POSender";
		String ReceiverName = "CPIReceiver";
		String mappingName = "MM_Global_Weather_Request";
		String senderChannelName = "HTTPSenderChannel";
		String ReceiverChannelName = "SOAPReceiverChannel";
		
		IFLWFileGenerator.generateIFLWXml(SenderName, ReceiverName, mappingName, senderChannelName, ReceiverChannelName);
		
	}
	/**
	 * 
	 * @param SenderName
	 * @param ReceiverName
	 * @param mappingName
	 * @param senderChannelName
	 * @param ReceiverChannel
	 */
	
	public static void generateIFLWXml(String SenderName, String ReceiverName, String mappingName, String senderChannelName, String ReceiverChannel) {
		
		
		SenderName = "POSender";
		ReceiverName = "CPIReceiver";
		mappingName = "MM_Global_Weather_Request";
		
		
	    try {
	    	
	    	
	   String firstPart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><bpmn2:definitions xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:ifl=\"http:///com.sap.ifl.model/Ifl.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"Definitions_1\">\r\n" + 
	   		"    <bpmn2:collaboration id=\"Collaboration_1\" name=\"Default Collaboration\">\r\n" + 
	   		"        <bpmn2:extensionElements>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>namespaceMapping</key>\r\n" + 
	   		"                <value>xmlns:p1=https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>allowedHeaderList</key>\r\n" + 
	   		"                <value/>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>httpSessionHandling</key>\r\n" + 
	   		"                <value>None</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>ServerTrace</key>\r\n" + 
	   		"                <value>false</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>returnExceptionToSender</key>\r\n" + 
	   		"                <value>false</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>log</key>\r\n" + 
	   		"                <value>All events</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>componentVersion</key>\r\n" + 
	   		"                <value>1.1</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>cmdVariantUri</key>\r\n" + 
	   		"                <value>ctype::IFlowVariant/cname::IFlowConfiguration/version::1.1.17</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"        </bpmn2:extensionElements>\r\n" + 
	   		"        ";
	   
	   
	   
	String senderParticipant = "<bpmn2:participant id=\"Participant_1\" ifl:type=\"EndpointSender\" name=\""+SenderName+"\">\r\n" + 
	   		"            <bpmn2:extensionElements>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>enableBasicAuthentication</key>\r\n" + 
	   		"                    <value>false</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>ifl:type</key>\r\n" + 
	   		"                    <value>EndpointSender</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"            </bpmn2:extensionElements>\r\n" + 
	   		"        </bpmn2:participant>\r\n" + 
	   		"        ";
	   
	   
	String receiverParticipant = "<bpmn2:participant id=\"Participant_2\" ifl:type=\"EndpointRecevier\" name=\""+ReceiverName+"\">\r\n" + 
	   		"            <bpmn2:extensionElements>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>ifl:type</key>\r\n" + 
	   		"                    <value>EndpointRecevier</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"            </bpmn2:extensionElements>\r\n" + 
	   		"        </bpmn2:participant>\r\n" + 
	   		"       ";
	   String integrationProcess = "<bpmn2:participant id=\"Participant_Process_1\" ifl:type=\"IntegrationProcess\" name=\"Integration Process\" processRef=\"Process_1\">\r\n" + 
	   		"            <bpmn2:extensionElements/>\r\n" + 
	   		"        </bpmn2:participant>\r\n" + 
	   		"        ";
	   
	   String senderChannel = new Scanner(new File("HTTPSenderChannel.xml")).useDelimiter("\\Z").next();
	   System.out.println(senderChannel);
	   
	   
	   String receiverChannel = new Scanner(new File("SOAPReceiverChannel.xml")).useDelimiter("\\Z").next();
	   System.out.println(receiverChannel);
	   
	   
	   String lastPart = "    </bpmn2:collaboration>\r\n" + 
	   		"    <bpmn2:process id=\"Process_1\" name=\"Integration Process\">\r\n" + 
	   		"        <bpmn2:extensionElements>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>transactionTimeout</key>\r\n" + 
	   		"                <value>30</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>componentVersion</key>\r\n" + 
	   		"                <value>1.1</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>cmdVariantUri</key>\r\n" + 
	   		"                <value>ctype::FlowElementVariant/cname::IntegrationProcess/version::1.1.3</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"            <ifl:property>\r\n" + 
	   		"                <key>transactionalHandling</key>\r\n" + 
	   		"                <value>Required</value>\r\n" + 
	   		"            </ifl:property>\r\n" + 
	   		"        </bpmn2:extensionElements>\r\n" + 
	   		"        <bpmn2:startEvent id=\"StartEvent_2\" name=\"Start\">\r\n" + 
	   		"            <bpmn2:extensionElements>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>componentVersion</key>\r\n" + 
	   		"                    <value>1.0</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>cmdVariantUri</key>\r\n" + 
	   		"                    <value>ctype::FlowstepVariant/cname::MessageStartEvent/version::1.0</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"            </bpmn2:extensionElements>\r\n" + 
	   		"            <bpmn2:outgoing>SequenceFlow_3</bpmn2:outgoing>\r\n" + 
	   		"            <bpmn2:messageEventDefinition/>\r\n" + 
	   		"        </bpmn2:startEvent>\r\n" + 
	   		"        <bpmn2:endEvent id=\"EndEvent_2\" name=\"End\">\r\n" + 
	   		"            <bpmn2:extensionElements>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>componentVersion</key>\r\n" + 
	   		"                    <value>1.1</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>cmdVariantUri</key>\r\n" + 
	   		"                    <value>ctype::FlowstepVariant/cname::MessageEndEvent/version::1.1.0</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"            </bpmn2:extensionElements>\r\n" + 
	   		"            <bpmn2:incoming>SequenceFlow_16</bpmn2:incoming>\r\n" + 
	   		"            <bpmn2:messageEventDefinition/>\r\n" + 
	   		"        </bpmn2:endEvent>\r\n" + 
	   		"        <bpmn2:callActivity id=\"CallActivity_8\" name=\"Request Mapping\">\r\n" + 
	   		"            <bpmn2:extensionElements>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>mappinguri</key>\r\n" + 
	   		"                    <value>dir://mmap/src/main/resources/mapping/"+mappingName+".mmap</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>mappingname</key>\r\n" + 
	   		"                    <value>"+mappingName+"</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>mappingType</key>\r\n" + 
	   		"                    <value>MessageMapping</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>mappingpath</key>\r\n" + 
	   		"                    <value>src/main/resources/mapping/"+mappingName+"</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>componentVersion</key>\r\n" + 
	   		"                    <value>1.1</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>activityType</key>\r\n" + 
	   		"                    <value>Mapping</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"                <ifl:property>\r\n" + 
	   		"                    <key>cmdVariantUri</key>\r\n" + 
	   		"                    <value>ctype::FlowstepVariant/cname::MessageMapping/version::1.1.0</value>\r\n" + 
	   		"                </ifl:property>\r\n" + 
	   		"            </bpmn2:extensionElements>\r\n" + 
	   		"            <bpmn2:incoming>SequenceFlow_3</bpmn2:incoming>\r\n" + 
	   		"            <bpmn2:outgoing>SequenceFlow_16</bpmn2:outgoing>\r\n" + 
	   		"        </bpmn2:callActivity>\r\n" + 
	   		"        <bpmn2:sequenceFlow id=\"SequenceFlow_3\" sourceRef=\"StartEvent_2\" targetRef=\"CallActivity_8\"/>\r\n" + 
	   		"        <bpmn2:sequenceFlow id=\"SequenceFlow_16\" sourceRef=\"CallActivity_8\" targetRef=\"EndEvent_2\"/>\r\n" + 
	   		"    </bpmn2:process>\r\n" + 
	   		"    <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\" name=\"Default Collaboration Diagram\">\r\n" + 
	   		"        <bpmndi:BPMNPlane bpmnElement=\"Collaboration_1\" id=\"BPMNPlane_1\">\r\n" + 
	   		"            <bpmndi:BPMNShape bpmnElement=\"EndEvent_2\" id=\"BPMNShape_EndEvent_2\">\r\n" + 
	   		"                <dc:Bounds height=\"32.0\" width=\"32.0\" x=\"778.0\" y=\"147.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNShape>\r\n" + 
	   		"            <bpmndi:BPMNShape bpmnElement=\"StartEvent_2\" id=\"BPMNShape_StartEvent_2\">\r\n" + 
	   		"                <dc:Bounds height=\"32.0\" width=\"32.0\" x=\"292.0\" y=\"142.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNShape>\r\n" + 
	   		"            <bpmndi:BPMNShape bpmnElement=\"CallActivity_8\" id=\"BPMNShape_CallActivity_8\">\r\n" + 
	   		"                <dc:Bounds height=\"60.0\" width=\"100.0\" x=\"474.0\" y=\"128.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNShape>\r\n" + 
	   		"            <bpmndi:BPMNShape bpmnElement=\"Participant_2\" id=\"BPMNShape_Participant_2\">\r\n" + 
	   		"                <dc:Bounds height=\"140.0\" width=\"100.0\" x=\"1016.0\" y=\"88.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNShape>\r\n" + 
	   		"            <bpmndi:BPMNShape bpmnElement=\"Participant_1\" id=\"BPMNShape_Participant_1\">\r\n" + 
	   		"                <dc:Bounds height=\"140.0\" width=\"100.0\" x=\"57.0\" y=\"100.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNShape>\r\n" + 
	   		"            <bpmndi:BPMNShape bpmnElement=\"Participant_Process_1\" id=\"BPMNShape_Participant_Process_1\">\r\n" + 
	   		"                <dc:Bounds height=\"220.0\" width=\"711.0\" x=\"240.0\" y=\"60.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNShape>\r\n" + 
	   		"            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_3\" id=\"BPMNEdge_SequenceFlow_3\" sourceElement=\"BPMNShape_StartEvent_2\" targetElement=\"BPMNShape_CallActivity_8\">\r\n" + 
	   		"                <di:waypoint x=\"308.0\" xsi:type=\"dc:Point\" y=\"158.0\"/>\r\n" + 
	   		"                <di:waypoint x=\"524.0\" xsi:type=\"dc:Point\" y=\"158.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNEdge>\r\n" + 
	   		"            <bpmndi:BPMNEdge bpmnElement=\"MessageFlow_4\" id=\"BPMNEdge_MessageFlow_4\" sourceElement=\"BPMNShape_Participant_1\" targetElement=\"BPMNShape_StartEvent_2\">\r\n" + 
	   		"                <di:waypoint x=\"107.0\" xsi:type=\"dc:Point\" y=\"170.0\"/>\r\n" + 
	   		"                <di:waypoint x=\"308.0\" xsi:type=\"dc:Point\" y=\"158.0\"/>\r\n" + 
	   		"            </bpmndi:BPMNEdge>\r\n" + 
	   		"            <bpmndi:BPMNEdge bpmnElement=\"MessageFlow_15\" id=\"BPMNEdge_MessageFlow_15\" sourceElement=\"BPMNShape_EndEvent_2\" targetElement=\"BPMNShape_Participant_2\">\r\n" + 
	   		"                <di:waypoint x=\"794.0\" xsi:type=\"dc:Point\" y=\"160.5\"/>\r\n" + 
	   		"                <di:waypoint x=\"1016.5\" xsi:type=\"dc:Point\" y=\"160.5\"/>\r\n" + 
	   		"            </bpmndi:BPMNEdge>\r\n" + 
	   		"            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_16\" id=\"BPMNEdge_SequenceFlow_16\" sourceElement=\"BPMNShape_CallActivity_8\" targetElement=\"BPMNShape_EndEvent_2\">\r\n" + 
	   		"                <di:waypoint x=\"524.0\" xsi:type=\"dc:Point\" y=\"160.5\"/>\r\n" + 
	   		"                <di:waypoint x=\"778.5\" xsi:type=\"dc:Point\" y=\"160.5\"/>\r\n" + 
	   		"            </bpmndi:BPMNEdge>\r\n" + 
	   		"        </bpmndi:BPMNPlane>\r\n" + 
	   		"    </bpmndi:BPMNDiagram>\r\n" + 
	   		"</bpmn2:definitions>";
	    	  
	   
	      FileWriter myWriter = new FileWriter("CPIFLowDeployment.iflw");
	      myWriter.write(firstPart+senderParticipant+receiverParticipant+integrationProcess+senderChannel+receiverChannel+lastPart);
	      myWriter.close();
	      	System.out.println("Successfully write to the file.");
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }
}
