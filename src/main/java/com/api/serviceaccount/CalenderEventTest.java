package com.api.serviceaccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.CalendarList;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
public class CalenderEventTest {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Event event = new Event();
        Calendar service =null;
        
        com.google.api.services.calendar.model.Events eventList;
		String message;
		
        event.setSummary("Testing Calendar ");
        event.setLocation("US");
        event.setDescription("Calendar");

        ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
        //attendees.add(new EventAttendee().setEmail("thiruvasagam@ation.co"));
        // ...
        //event.setAttendees(attendees);   


        // set the number of days
        java.util.Calendar startCal = java.util.Calendar.getInstance();
        startCal.set(java.util.Calendar.MONTH, 6);
        startCal.set(java.util.Calendar.DATE, 4);
        startCal.set(java.util.Calendar.HOUR_OF_DAY, 19);
        startCal.set(java.util.Calendar.MINUTE, 0);
        Date startDate = startCal.getTime();

        java.util.Calendar endCal = java.util.Calendar.getInstance();
        endCal.set(java.util.Calendar.MONTH, 6);
        endCal.set(java.util.Calendar.DATE, 4);
        endCal.set(java.util.Calendar.HOUR_OF_DAY, 20);
        endCal.set(java.util.Calendar.MINUTE, 0);
        Date endDate = endCal.getTime();


        DateTime start = new DateTime(startDate);
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(endDate);
        event.setEnd(new EventDateTime().setDateTime(end));

        service = new CalendarService().configure();
        final DateTime date1 = new DateTime("2017-05-05T16:30:00.000+05:30");
    	final DateTime date2 = new DateTime(new Date());
        /*
        final DateTime date1 = new DateTime("2017-05-05T16:30:00.000+05:30");
    	final DateTime date2 = new DateTime(new Date());
        Events events = service.events();
		eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
		message = eventList.getItems().toString();
		System.out.println("My:" + eventList.getItems());
		java.util.List<Event> items = eventList.getItems();
		for (Event event1 : items) {  
		    System.out.println("Events Sum"+event1.getSummary());
		  } 
				  //*/

		Event createdEvent = service.events().insert("primary", event).execute();

        System.out.println("Data is :"+createdEvent.getId()); 
        System.out.println("Event date Time :"+createdEvent.getSummary());
          
        Events events = service.events();
		eventList = events.list("ation.co_1883ig50hir22j8lhb1g5cspn8lsi@resource.calendar.google.com").execute();
		List<Event> itemsEvent=eventList.getItems();
		System.out.println("itemsEvent"+itemsEvent);
		
		for (Event event2 : itemsEvent) {
			System.out.println("Ev :"+event2.getSummary());
		}
		System.out.println("My:" + eventList.getItems().size());
    	//Event createdEvent = service.events().insert("primary", event).execute();

        System.out.println("Data is :"+createdEvent.getId()); 
        System.out.println("Event date Time :"+createdEvent.getSummary());
        System.out.println("Event "+event.getHtmlLink()); 
        com.google.api.services.calendar.model.Calendar calendar =
        	    service.calendars().get("primary").execute();

        	System.out.println("Calendar :"+calendar.getId());  
		
		// Iterate through entries in calendar list
		String pageToken = null;
		do {
		  com.google.api.services.calendar.model.CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
		  List<CalendarListEntry> items = calendarList.getItems();
		  System.out.println("calendar :"+calendarList.isEmpty());
		  calendarList.isEmpty();
		  System.out.println("calendarlist items :"+calendarList.getItems());
		  event = service.events().insert("ation.co_3132s0dhk5n8ht1l0qgnb8t2rk@group.calendar.google.com", event).execute();
		  for (CalendarListEntry calendarListEntry : items) {
		   // System.out.println("calendar List"+calendarListEntry.getSummary());
		    System.out.println("calendar ID"+calendarListEntry.getId());
		    //event = service.events().insert("ation.co_1883ig50hir22j8lhb1g5cspn8lsi@resource.calendar.google.com", event).execute();
		    
		    System.out.println("Event "+event.getHtmlLink());
		  } 
		  pageToken = calendarList.getNextPageToken();
		} while (pageToken != null);
    }
}
