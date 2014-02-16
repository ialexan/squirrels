/**
 * File.java
 * 
 * $Author: ialexan $
 */
package edu.csula.squirrels.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "files")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;

    private String type;

    private Long size;

    @Column(nullable = false)
    private Date date;

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false, name = "owner_id")
    private User owner;

    @Column(nullable = false)
    private boolean deleted;
    
    /**
     * <code>baseDir</code> is the physical directory on disk where all the Bookpub
     * files are stored. Since this directory may be different on different
     * servers, the <code>baseDir</code> property is not stored in the database
     * - instead, it should be set by the client code (usually a controller)
     * before read() or writer() can be performed.
     */
    @Transient
    private String baseDir;

    public File()
    {
        date = new Date();
        deleted = false;
    }
    
    public File(String name){
    	super();
    	this.name = name;
    }

    public java.io.File getDiskFile()
    {
        return new java.io.File( baseDir, name );
    }
    
    public void setBaseDir( String baseDir )
    {
        this.baseDir = baseDir;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize( Long size )
    {
        this.size = size;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner( User owner )
    {
        this.owner = owner;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted( boolean deleted )
    {
        this.deleted = deleted;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    

}
