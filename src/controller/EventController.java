package controller;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.EventDAO;
import entities.Event;

@Controller
public class EventController
{
	@Autowired
	private EventDAO eventDAO;
	
	@ResponseBody
	@RequestMapping("ping")
	public String ping()
	{
		return "pong";
	}
	
	@ResponseBody
	@RequestMapping("event1")
	public Event getEvent()
	{
		Event e = new Event (135.4);
		return e;
	}
	
	@ResponseBody
	@RequestMapping("events")
	public List<Event> getEvents () {
		return eventDAO.getEvents();
	}
	
	@ResponseBody
	@RequestMapping("event/{id}")
	public Event getEventById(@PathVariable int id)
	{
		Event e = eventDAO.getEventById(id);
		System.out.println(e);
		return e;
	}
	
	@ResponseBody
	@RequestMapping(path = "event", method = RequestMethod.PUT)
	public void createEvent(@RequestBody Event event) 
	{
		System.out.println(event);
		eventDAO.createEvent(event);
	}

	@ResponseBody
	@RequestMapping(path = "event/{id}", method = RequestMethod.DELETE)
	public void deleteEvent(@PathVariable int id)
	{
		eventDAO.deleteEvent(id);
	}
	@ResponseBody
	@RequestMapping("event2/{date}")
	public Event getEventByDate(@PathVariable("date") String d)
	{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try
		{
			date = df.parse(d);
		} catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Event e = eventDAO.getEventByDate(sqlDate);
		System.out.println(e);
		return e;
	}
}