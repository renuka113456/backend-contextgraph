package com.contextgraph.model;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="documents", indexes={@Index(name="idx_doc_owner_created",columnList="owner_id,created_at"),@Index(name="idx_doc_checksum_owner",columnList="checksum,owner_id")})
public class StoredDocument {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="owner_id",nullable=false) private AppUser owner;
 @Column(nullable=false) private String originalName; @Column(nullable=false) private String storedName;
 @Column(nullable=false,length=64) private String checksum; @Column(nullable=false) private long sizeBytes;
 @Column(columnDefinition="TEXT") private String extractedText=""; @Column(nullable=false) private Instant createdAt=Instant.now();
 public Long getId(){return id;} public AppUser getOwner(){return owner;} public void setOwner(AppUser v){owner=v;} public String getOriginalName(){return originalName;} public void setOriginalName(String v){originalName=v;} public String getStoredName(){return storedName;} public void setStoredName(String v){storedName=v;} public String getChecksum(){return checksum;} public void setChecksum(String v){checksum=v;} public long getSizeBytes(){return sizeBytes;} public void setSizeBytes(long v){sizeBytes=v;} public String getExtractedText(){return extractedText;} public void setExtractedText(String v){extractedText=v;} public Instant getCreatedAt(){return createdAt;}
}
