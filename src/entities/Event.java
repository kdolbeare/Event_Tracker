package entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "weight")
public class Event
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "weigh_in_date")
	private Date eventDate;
		
	private double weight;
	
	@Column(name = "bodyfat_pct")
	private double bodyfatPct;
	
	@Column(name = "musclemass_pct")
	private double musclemassPct;
	
	@Column(name = "water_pct")
	private double waterPct;
	
	private String comment;
	

	public Event()
	{
		super();
	}
	public Event(double weight)
	{
		this();
		this.weight = weight;
	}
	public Event(Date eventDate, double weight, double bodyfatPct, double musclemassPct, double waterPct,
			String comment)
	{
		this(weight);
		this.eventDate = eventDate;
		this.bodyfatPct = bodyfatPct;
		this.musclemassPct = musclemassPct;
		this.waterPct = waterPct;
		this.comment = comment;
	}

	public int getId()
	{
		return id;
	}
	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public Date getEventDate()
	{
		return eventDate;
	}

	public void setEventDate(Date eventDate)
	{
		this.eventDate = eventDate;
	}

	public double getBodyfatPct()
	{
		return bodyfatPct;
	}

	public void setBodyfatPct(double bodyfatPct)
	{
		this.bodyfatPct = bodyfatPct;
	}

	public double getMusclemassPct()
	{
		return musclemassPct;
	}

	public void setMusclemassPct(double musclemassPct)
	{
		this.musclemassPct = musclemassPct;
	}

	public double getWaterPct()
	{
		return waterPct;
	}

	public void setWaterPct(double waterPct)
	{
		this.waterPct = waterPct;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
	@Override
	public String toString()
	{
		return "Event [eventDate= " + eventDate + ", weight= " + weight + ", bodyfatPct= " + bodyfatPct
				+ ", musclemassPct= " + musclemassPct + ", waterPct= " + waterPct + ", comment= " + comment + "]";
	}

}
