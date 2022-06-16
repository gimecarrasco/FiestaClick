package com.fiestaClick.demo.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
//import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

//Data
@Entity
public class PhotoEntity {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;



    public PhotoEntity() {
    }

    public PhotoEntity(String id, String name, String mime, byte[] content) {
        this.id = id;
        this.name = name;
        this.mime = mime;
        this.content = content;
      
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

   
    @Override
    public String toString() {
        return "PhotoEntity{" + "id=" + id + ", name=" + name + ", mime=" + mime + ", content=" + content + '}';
    }
          
    
}
