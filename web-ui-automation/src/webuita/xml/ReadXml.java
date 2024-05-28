package webuita.xml;

import java.io.*;
import java.util.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import webuita.locating.Column;
import webuita.locating.Item;


public  class ReadXml {
	
	public static Map<String, Item> getLocators(String locationFilePath ){
		Map<String, Item> locatingMap = new HashMap<String, Item>();
		Item item = new Item();
		Column column = new Column();
		HashMap<String, Column> columns = new HashMap<String, Column>();
		
		boolean bItem = false;
		boolean bByType = false;
		boolean bName = false;
		boolean bType = false;
		boolean bByValue = false;
		boolean bColumns = false;
		boolean bColumn = false;
		
		try{
			
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			inputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
			InputStream in = new FileInputStream(locationFilePath);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();
				switch(event.getEventType()){
					case XMLStreamConstants.START_ELEMENT:
						if (event.isStartElement()){
							
							StartElement startElement = event.asStartElement();
							String qNameStart = startElement.getName().getLocalPart();
							if(qNameStart.equalsIgnoreCase("Items")){
								Iterator<Attribute> attributes = startElement.getAttributes();
								if(attributes.hasNext()){
									String title = attributes.next().getValue();
									
								}
								
							}else if(qNameStart.equalsIgnoreCase("Item")){
								item = new Item();
								bItem = true;
							}else if(qNameStart.equalsIgnoreCase("ByValue")){
								
								bByValue = true;
							}else if(qNameStart.equalsIgnoreCase("Name")){
								bName = true;
							}else if(qNameStart.equalsIgnoreCase("ByType")){
								bByType = true;
							}else if(qNameStart.equalsIgnoreCase("Type")){
								bType = true;
							}else if(qNameStart.equalsIgnoreCase("Columns")){
								columns = new HashMap<String, Column>();
								bColumns = true;
							}else if(qNameStart.equalsIgnoreCase("Column")){
								column = new Column();
								bColumn = true;
							}
						}
						
						break;
					case XMLStreamConstants.CHARACTERS:
						
						if( bByValue && bItem && !bColumn){
							Characters characters = event.asCharacters();
							item.setByValue(characters.getData());
							bByValue = false;
						}
						if(bName && bItem && !bColumn){
							Characters characters = event.asCharacters();
							item.setName(characters.getData());
							bName = false;
						}
						if(bByType && bItem && !bColumn){
							Characters characters = event.asCharacters();
							item.setByType(characters.getData());
							bByType = false;
						}
						if(bType && bItem && !bColumn){
							Characters characters = event.asCharacters();
							item.setType(characters.getData());
							bType = false;
						}
												
						if(bName && bItem && bColumn){
							Characters characters = event.asCharacters();
							column.setName(characters.getData());
							bName = false;
						}
						if(bByType && bItem && bColumn){
							Characters characters = event.asCharacters();
							column.setByType(characters.getData());
							bByType = false;
						}
						if( bByValue && bItem && bColumn){
							Characters characters = event.asCharacters();
							column.setByValue(characters.getData());
							bByValue = false;
						}
						if(bType && bItem && bColumn){
							Characters characters = event.asCharacters();
							column.setType(characters.getData());
							bType = false;
						}
					 case XMLStreamConstants.END_ELEMENT:
						 if(event.isEndElement()){
							 EndElement endElement = event.asEndElement();
							 String qNameEnd = endElement.getName().getLocalPart();
							 if(qNameEnd.equalsIgnoreCase("Column")){
								 columns.put(column.getName(), column);
								 bColumn = false;
								 
							 }
							 if(qNameEnd.equalsIgnoreCase("Item")){
								 locatingMap.put(item.getName(), item);
								 bItem = false;
								 
							 }
							 if(qNameEnd.equalsIgnoreCase("Columns")){
								 item.setColumns(columns);
								 bColumns = false;
								 columns = new HashMap<String, Column>();
							 }
							
						 }
						
						 break;
					 default:
						 break;
				}
			}
		}catch(Exception e){
			
		} 
		return locatingMap;
	}

}
