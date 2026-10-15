package com.contextgraph.repository;
import com.contextgraph.model.StoredDocument; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface DocumentRepository extends JpaRepository<StoredDocument,Long>{ List<StoredDocument> findByOwnerIdOrderByCreatedAtDesc(Long ownerId); Optional<StoredDocument> findByIdAndOwnerId(Long id,Long ownerId); boolean existsByChecksumAndOwnerId(String checksum,Long ownerId); List<StoredDocument> findByOwnerIdAndExtractedTextContainingIgnoreCaseOrderByCreatedAtDesc(Long ownerId,String term); }
