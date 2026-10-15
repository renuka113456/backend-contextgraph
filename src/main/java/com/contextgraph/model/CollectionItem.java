package com.contextgraph.model;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="collections")
public class CollectionItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="owner_id",nullable=false) private AppUser owner;
 @Column(nullable=false,length=120) private String name; @Column(length=500) private String description=""; @Column(nullable=false) private Instant createdAt=Instant.now();
 public Long getId(){return id;} public AppUser getOwner(){return owner;} public void setOwner(AppUser v){owner=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public Instant getCreatedAt(){return createdAt;}
}
