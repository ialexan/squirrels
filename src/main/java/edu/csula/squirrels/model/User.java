package edu.csula.squirrels.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false)
    private boolean enabled;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "authorities",
        joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    public User()
    {
        enabled = true;
        roles = new HashSet<String>();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( String role : roles )
            authorities.add( new SimpleGrantedAuthority( role ) );
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }

    public Set<String> getRoles()
    {
        return roles;
    }

    public void setRoles( Set<String> roles )
    {
        this.roles = roles;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

}
