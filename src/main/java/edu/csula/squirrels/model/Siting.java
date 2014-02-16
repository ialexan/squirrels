package edu.csula.squirrels.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "sitings")
public class Siting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "species", nullable = false)
    private Species species;

    private double longitude, latitude;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "submitted_by")
    private User submittedBy;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private User verifiedBy;
    
    @OneToOne
    @JoinColumn(name = "image_id")
    private File sightingImage;
    
    @JsonIgnore
    @Column(nullable = false)
    private boolean rejected;

    public Siting(){
    	
    }
    
    public Siting(Date timestamp, double latitude, double longitude, Species species, User submittedBy){
    	this.species = species;
    	this.timestamp = timestamp;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.submittedBy = submittedBy;
    }
    
    public Siting(Date timestamp, double latitude, double longitude, Species species, User submittedBy, File sightingImage){
    	this.species = species;
    	this.timestamp = timestamp;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.submittedBy = submittedBy;
    	this.sightingImage = sightingImage;
    }
    
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Species getSpecies()
    {
        return species;
    }

    public void setSpecies( Species species )
    {
        this.species = species;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude( double longitude )
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude( double latitude )
    {
        this.latitude = latitude;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp( Date timestamp )
    {
        this.timestamp = timestamp;
    }

    public User getSubmittedBy()
    {
        return submittedBy;
    }

    public void setSubmittedBy( User submittedBy )
    {
        this.submittedBy = submittedBy;
    }

    public User getVerifiedBy()
    {
        return verifiedBy;
    }

    public void setVerifiedBy( User verifiedBy )
    {
        this.verifiedBy = verifiedBy;
    }

	public File getSightingImage() {
		return sightingImage;
	}

	public void setSightingImage(File sightingImage) {
		this.sightingImage = sightingImage;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
    
    

}
