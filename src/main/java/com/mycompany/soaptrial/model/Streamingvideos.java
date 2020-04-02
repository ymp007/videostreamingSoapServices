/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soaptrial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YMP
 */
@Entity
@Table(name = "STREAMINGVIDEOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Streamingvideos.findAll", query = "SELECT s FROM Streamingvideos s"),
    @NamedQuery(name = "Streamingvideos.findById", query = "SELECT s FROM Streamingvideos s WHERE s.id = :id"),
    @NamedQuery(name = "Streamingvideos.findByTitle", query = "SELECT s FROM Streamingvideos s WHERE s.title = :title"),
    @NamedQuery(name = "Streamingvideos.findByLink", query = "SELECT s FROM Streamingvideos s WHERE s.link = :link"),
    @NamedQuery(name = "Streamingvideos.findByAdmins", query = "SELECT s FROM Streamingvideos s WHERE s.admins = :admins")})
public class Streamingvideos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 350)
    @Column(name = "LINK")
    private String link;
    @Lob
    @Column(name = "THUMBNAIL")
    private byte[] thumbnail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ADMINS")
    private String admins;

    public Streamingvideos() {
    }

    public Streamingvideos(BigDecimal id) {
        this.id = id;
    }

    public Streamingvideos(BigDecimal id, String title, String link, String admins) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.admins = admins;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAdmins() {
        return admins;
    }

    public void setAdmins(String admins) {
        this.admins = admins;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Streamingvideos)) {
            return false;
        }
        Streamingvideos other = (Streamingvideos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Streamingvideos{" + "id=" + id + ", title=" + title + ", link=" + link + ", thumbnail=" + thumbnail + ", admins=" + admins + '}';
    }

    
    
}
